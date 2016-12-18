package pro.xstore.api.sync;

import pro.xstore.api.message.command.BaseCommand;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.sync.ServerData.ServerEnum;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncAPIConnector extends StreamingConnector {

	public final static String WRAPPER_VERSION = "2.4.7";
	public static boolean throwOnError = true;

	public final static int MAX_REDIRECTS = 3;
	private final static int CONNECT_TIMEOUT_IN_SECONDS = 5;

	private Map<String, Long> commandToTimestamp = new HashMap<String, Long>();
	private SocketFactory socketFactory;
	private boolean socketFactoryCreated = false;

	/**
	 * In order to use the wrapper in the multithreaded environment, multiple instances of this class have to be created (separate sessions).
	 * @param server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public SyncAPIConnector(ServerEnum server) throws UnknownHostException, IOException {
		Server serverObj = ServerData.getServerByEnum(server);
		boolean isFromLoginRedirect = false;
		setUp(serverObj, isFromLoginRedirect);
	}

    public void setThrowOnErrorFalse() {
    	throwOnError = false;
    }

	private void setUp(Server server, boolean isFromLoginRedirect) throws IOException {
		this.server = server;
		streamConnected = false;
		
		String address = this.server.getAddress();
		int port = this.server.getMainPort();
		boolean partOfXapiList = this.server.isPartOfXapiList();
		boolean tryNextServer = partOfXapiList && !isFromLoginRedirect;
		boolean secure = this.server.isSecure();
		int timeout_in_millis = CONNECT_TIMEOUT_IN_SECONDS * 1000;
		InetSocketAddress socketAddress = new InetSocketAddress(address, port);
		if (this.server.isSecure()) {
            // create a trust manager that does not validate certificate chains to avoid problems with certificates that are not installed in the local Java cert store
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
            };
            if (!socketFactoryCreated) {
	            // install the all-trusting trust manager
	            try {
	                SSLContext sc = SSLContext.getInstance("TLS");
	                sc.init(null, trustAllCerts, new SecureRandom());
	                SSLContext.setDefault(sc);
	            } catch (Exception ignore) {}
	
				socketFactory = SSLSocketFactory.getDefault();
				socketFactoryCreated = true;
            }
			try {
				this.connect(socketAddress, timeout_in_millis, tryNextServer, secure);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				this.connect(socketAddress, timeout_in_millis, tryNextServer, secure);
			} catch (IOException ex) {
				throw ex;
			}
		}
		writer = new PrintStream(socket.getOutputStream());
		InputStream apiReadStream = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(apiReadStream));
		connected = true;
	}

	public boolean isConnected() {
		return super.connected;
	}
	
	private void connect(InetSocketAddress socketAddress, int timeout_in_millis, boolean tryNextServer, boolean secure) throws IOException {
		if (tryNextServer) {
			try {
				this.createSocketHelper(secure);
				socket.connect(socketAddress, timeout_in_millis);
			} catch (IOException ex) {
				String nextOrNull = ServerData.getNextAddress();
				if (nextOrNull == null) {
					throw new ConnectException("end of xapi list");
				}
				String nextAddress = nextOrNull;
				this.server.setAddress(nextAddress);
				InetSocketAddress ia = new InetSocketAddress(nextAddress, socketAddress.getPort());
				boolean innerTryNextServer = true;
				this.connect(ia, timeout_in_millis, innerTryNextServer, secure);
			}
		} else {
			this.createSocketHelper(secure);
			socket.connect(socketAddress, timeout_in_millis);
		}
	}
	
	private void createSocketHelper (boolean secure) throws IOException {
		socket = secure ? socketFactory.createSocket() : new Socket();
	}
	
	public void redirect(Server server) throws APICommunicationException {
		if(server != null && connected) {
			boolean wasStreamConnected = this.streamConnected;
			StreamingListener oldSl = this.sl;
			
			if(wasStreamConnected) {
				this.disconnectStream();
			}
			this.close();
			try {
				boolean isFromLoginRedirect = true;
				this.setUp(server, isFromLoginRedirect);
				if(wasStreamConnected) {
					this.connectStream(oldSl);
				}
			} catch (IOException ignore) {}
		}
	}

	/**
	 * Execute command withholding API inter-command timeout
	 */
	public synchronized String safeExecuteCommand(BaseCommand cmd) throws APICommunicationException {
		String cmdName = cmd.getCommandName();
		Long lastTimeExecuted = this.commandToTimestamp.get(cmdName);
		Long cmdTimeout = cmd.getTimeoutMillis();
		Long timeToWait;
		if (lastTimeExecuted == null) {
			timeToWait = 0L;
		} else {
			Calendar cal = Calendar.getInstance();
			timeToWait = cmdTimeout - (cal.getTimeInMillis() - lastTimeExecuted);
		}

		if (timeToWait > 0L) {
			try {
				Thread.sleep(timeToWait);
			} catch (InterruptedException ex) {
				Logger.getLogger(SyncAPIConnector.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		String js = cmd.toJSONString();
		String result = this.executeCommandNoTimeout(js);
		if(result.isEmpty()) {
			throw new APICommunicationException("Server not responding");   
		}
		
		Calendar cal = Calendar.getInstance();
		this.commandToTimestamp.put(cmdName, cal.getTimeInMillis());
		return result;
	}

	private String executeCommandNoTimeout(String message) throws APICommunicationException {
		this.writeMessage(message);
		return this.readMessage();
	}

	private void writeMessage(String message) throws APICommunicationException {
		try {
			this.writeMessageHelper(message, false);
		} catch (APICommunicationException ex) {
			this.disconnectStream();
			this.close();
			throw ex;
		}
	}

	private String readMessage() throws APICommunicationException {
		StringBuilder sb = new StringBuilder();
		String newline = null;
		String messageString = null;
		boolean readDone = false;
		boolean sockOK = true;
		char lastChar = ' ';
		try {
			while (!readDone
					&& (sockOK = this.checkSocketState(APISocketOperation.READ))
					&& ((newline = reader.readLine()) != null)) {
				
				newline = newline.trim();
				
				if ("".equals(newline) && lastChar == '}') {
					readDone = true;
				} else {
					sb.append(newline);
					
					if (newline.length() != 0) {
						lastChar = newline.charAt(newline.length() - 1);
					}
				}
				newline = null;
			}
			if (!sockOK) {
				throw new APICommunicationException("Read error. Socket state invalid.");
			}
		} catch (IOException ex) {
			this.disconnectStream();
			this.close();
			throw new APICommunicationException(ex.getMessage());
		}
		messageString = sb.toString();
		return messageString;
	}

	/**
	 * Cleans up this object and closes the socket
	 */
	public void close() throws APICommunicationException {
		try {
			this.socket.close();
			connected = false;
		} catch (IOException ignore) {}
	}
}