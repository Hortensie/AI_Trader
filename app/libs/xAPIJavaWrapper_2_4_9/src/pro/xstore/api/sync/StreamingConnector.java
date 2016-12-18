package pro.xstore.api.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.records.BaseResponseRecord;
import pro.xstore.api.message.records.SBalanceRecord;
import pro.xstore.api.message.records.SCandleRecord;
import pro.xstore.api.message.records.SKeepAliveRecord;
import pro.xstore.api.message.records.SNewsRecord;
import pro.xstore.api.message.records.SProfitRecord;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.STradeRecord;
import pro.xstore.api.message.records.STradeStatusRecord;
import pro.xstore.api.streaming.BalanceStop;
import pro.xstore.api.streaming.BalanceSubscribe;
import pro.xstore.api.streaming.CandlesStop;
import pro.xstore.api.streaming.CandlesSubscribe;
import pro.xstore.api.streaming.KeepAliveStop;
import pro.xstore.api.streaming.KeepAliveSubscribe;
import pro.xstore.api.streaming.NewsStop;
import pro.xstore.api.streaming.NewsSubscribe;
import pro.xstore.api.streaming.ProfitsStop;
import pro.xstore.api.streaming.ProfitsSubscribe;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.streaming.TickPricesStop;
import pro.xstore.api.streaming.TickPricesSubscribe;
import pro.xstore.api.streaming.TradeRecordsStop;
import pro.xstore.api.streaming.TradeRecordsSubscribe;
import pro.xstore.api.streaming.TradeStatusRecordsStop;
import pro.xstore.api.streaming.TradeStatusRecordsSubscribe;

public class StreamingConnector extends Connector {
	
	protected Thread streamReaderThread;
	protected String streamSessionId;
	protected StreamingListener sl;
	
	public void connectStream(StreamingListener strl) throws UnknownHostException, IOException, APICommunicationException {
		if (this.streamSessionId == null) {
			throw new APICommunicationException("please login first");
		}

		if (streamConnected) {
			throw new APICommunicationException("stream already connected");
		}

		String address = server.getAddress();
		int portStream = server.getStreamingPort();
		if (server.isSecure()) {
			SocketFactory socketFactory = SSLSocketFactory.getDefault();
			streamSocket = socketFactory.createSocket(address, portStream);
		} else {
			streamSocket = new Socket(address, portStream);
		}

		streamWriter = new PrintStream(streamSocket.getOutputStream());
		InputStream streamingReadStream = streamSocket.getInputStream();
		streamReader = new BufferedReader(new InputStreamReader(streamingReadStream));
		this.sl = strl;
		streamConnected = true;
		streamReaderThread = createStreamingReaderThread();
		streamReaderThread.start();
	}
	
	public void disconnectStream() {
		if (streamConnected) {
			streamConnected = false;
			try {
				this.closeStream();
			} catch (APICommunicationException ignore) {}
		}
	}

	private void closeStream() throws APICommunicationException {
		try {
			this.streamReaderThread.interrupt();
			this.streamSocket.close();
		} catch (IOException ex) {
			throw new APICommunicationException(ex.getMessage());
		}
	}
	
	public void setStreamSessionId(String streamSessionId) {
		this.streamSessionId = streamSessionId;
	}
	
	/**
	 * Subscribes for prices of given symbols
	 * @param symbols list of symbols
	 * @throws APICommunicationException
	 */
	public void subscribePrices(List<String> symbols) throws APICommunicationException {
		for (String symbol : symbols) {
			subscribePrice(symbol);
		}
	}

	/**
	 * Subscribes for prices of a chosen symbol with the given minimum arrival time in milliseconds
	 */
	public void subscribePrice(String symbol, int minArrivalTime) throws APICommunicationException {
		TickPricesSubscribe tps = new TickPricesSubscribe(symbol, streamSessionId, minArrivalTime);
		writeMessageToStream(tps.toJSONString());
	}

	/**
	 * Subscribes for prices of a chosen symbol with the given minimum arrival time in milliseconds and the given maxLevel
	 */
	public void subscribePrice(String symbol, int minArrivalTime, int maxLevel) throws APICommunicationException {
		TickPricesSubscribe tps = new TickPricesSubscribe(symbol, streamSessionId, minArrivalTime, maxLevel);
		writeMessageToStream(tps.toJSONString());
	}

	/**
	 * Subscribes for prices of a chosen symbol with the given maxLevel
	 */
	public void subscribePrice(int maxLevel, String symbol) throws APICommunicationException {
		TickPricesSubscribe tps = new TickPricesSubscribe(symbol, maxLevel, streamSessionId);
		writeMessageToStream(tps.toJSONString());
	}
	
	/**
	 * Subscribes for prices of a chosen symbol
	 */
	public void subscribePrice(String symbol) throws APICommunicationException {
		TickPricesSubscribe tps = new TickPricesSubscribe(symbol, streamSessionId);
		writeMessageToStream(tps.toJSONString());
	}

	/**
	 * Unsubscribes prices of a symbol
	 */
	public void unsubscribePrice(String symbol) throws APICommunicationException {
		TickPricesStop tps = new TickPricesStop(symbol);
		writeMessageToStream(tps.toJSONString());
	}

	/**
	 * Unsubscribes prices of given symbols
	 */
	public void unsubscribePrices(List<String> symbols) throws APICommunicationException {
		for (String symbol : symbols) {
			unsubscribePrice(symbol);
		}
	}

	/**
	 * Subscribes to trade updates
	 */
	public void subscribeTrades() throws APICommunicationException {
		TradeRecordsSubscribe trs = new TradeRecordsSubscribe(streamSessionId);
		writeMessageToStream(trs.toJSONString());
	}

	/**
	 * Unsubscribes trade updates
	 */
	public void unsubscribeTrades() throws APICommunicationException {
		TradeRecordsStop trs = new TradeRecordsStop();
		writeMessageToStream(trs.toJSONString());
	}

	/**
	 * Subscribes to news updates
	 */
	public void subscribeNews() throws APICommunicationException {
		NewsSubscribe ns = new NewsSubscribe(streamSessionId);
		writeMessageToStream(ns.toJSONString());
	}
	
	/**
	 * Unsubscribes news updates
	 */
	public void unsubscribeNews() throws APICommunicationException {
		NewsStop ns = new NewsStop();
		writeMessageToStream(ns.toJSONString());
	}
	
	/**
	 * Subscribes to profits updates
	 */
	public void subscribeProfits() throws APICommunicationException {
		ProfitsSubscribe ps = new ProfitsSubscribe(streamSessionId);
		writeMessageToStream(ps.toJSONString());
	}

	/**
	 * Unsubscribes profits updates
	 */
	public void unsubscribeProfits() throws APICommunicationException {
		ProfitsStop ps = new ProfitsStop();
		writeMessageToStream(ps.toJSONString());
	}
	
	/**
	 * Subscribes to balance updates
	 */
	public void subscribeBalance() throws APICommunicationException {
		BalanceSubscribe brs = new BalanceSubscribe(streamSessionId);
		writeMessageToStream(brs.toJSONString());
	}
	
	/**
	 * Unsubscribes balance updates
	 */
	public void unsubscribeBalance() throws APICommunicationException {
		BalanceStop brs = new BalanceStop();
		writeMessageToStream(brs.toJSONString());
	}
	
	/**
	 * Subscribes for chart candles of given symbols
	 * @param symbols list of symbols
	 * @throws APICommunicationException
	 */
	public void subscribeCandles(List<String> symbols) throws APICommunicationException {
		for (String symbol : symbols) {
			subscribeCandle(symbol);
		}
	}

	/**
	 * Subscribes for chart candles of a chosen symbol
	 */
	public void subscribeCandle(String symbol) throws APICommunicationException {
		CandlesSubscribe cs = new CandlesSubscribe(symbol, streamSessionId);
		writeMessageToStream(cs.toJSONString());
	}

	/**
	 * Unsubscribes chart candles of a symbol
	 */
	public void unsubscribeCandle(String symbol) throws APICommunicationException {
		CandlesStop cs = new CandlesStop(symbol);
		writeMessageToStream(cs.toJSONString());
	}

	/**
	 * Unsubscribes chart candles of given symbols
	 */
	public void unsubscribeCandles(List<String> symbols) throws APICommunicationException {
		for (String symbol : symbols) {
			unsubscribeCandle(symbol);
		}
	}
	
	/**
	 * Subscribes to keep alive messages
	 */
	public void subscribeKeepAlive() throws APICommunicationException {
		KeepAliveSubscribe kas = new KeepAliveSubscribe(streamSessionId);
		writeMessageToStream(kas.toJSONString());
	}
	
	/**
	 * Unsubscribes keep alive messages
	 */
	public void unsubscribeKeepAlive() throws APICommunicationException {
		KeepAliveStop kas = new KeepAliveStop();
		writeMessageToStream(kas.toJSONString());
	}
	
	/**
	 * Subscribes to trade status updates
	 */
	public void subscribeTradeStatus() throws APICommunicationException {
		TradeStatusRecordsSubscribe rsrs = new TradeStatusRecordsSubscribe(streamSessionId);
		writeMessageToStream(rsrs.toJSONString());
	}

	/**
	 * Unsubscribes trade status updates
	 */
	public void unsubscribeTradeStatus() throws APICommunicationException {
		TradeStatusRecordsStop rsrs = new TradeStatusRecordsStop();
		writeMessageToStream(rsrs.toJSONString());
	}

	private void writeMessageToStream(String message) throws APICommunicationException {
		try {
			this.writeMessageHelper(message, true);
		} catch (APICommunicationException ex) {
			this.disconnectStream();
			throw ex;
		}
	}
	
	protected Thread createStreamingReaderThread() {
		Thread response = new Thread(new Runnable() {
			@Override
			public void run() {
				while (streamConnected) {
					try {
						BaseResponseRecord brr = null;
						String message = null;
						if ((message = readMessageFromStream()) != null) {
							JSONObject jsonMsg = (JSONObject) JSONValue.parseWithException(message);
							if (jsonMsg != null && jsonMsg.containsKey("command") && jsonMsg.containsKey("data")) {
								String command = (String) jsonMsg.get("command");
								JSONObject data = (JSONObject) jsonMsg.get("data");
								if (command != null) {
									brr = createResponseRecord(command);
									brr.setFieldsFromJSONObject(data);
									invokeListener(command, brr);
								}
							}
						}
					} catch (Exception ex) {
						if (ex != null) {
							if (ex.getMessage() != null) {
								if (ex.getMessage().contains("Connection reset")) {
									streamConnected = false;
								} else if (ex.getMessage().contains("Software caused connection abort: recv failed")) {
									streamConnected = false;
								}
							}
							ex.printStackTrace();
						}
					}
				}
			}
		});
		return response;
	}

	private static BaseResponseRecord createResponseRecord(String command) {
		BaseResponseRecord result = null;
		if (command != null) {
			if (command.equals("tickPrices")) result = new STickRecord();
			else if (command.equals("trade")) result = new STradeRecord();
			else if (command.equals("balance")) result = new SBalanceRecord();
			else if (command.equals("tradeStatus")) result = new STradeStatusRecord();
			else if (command.equals("profit")) result = new SProfitRecord();
			else if (command.equals("news")) result = new SNewsRecord();
			else if (command.equals("keepAlive")) result = new SKeepAliveRecord();
			else if (command.equals("candle")) result = new SCandleRecord();
		}
		return result;
	}

	private void invokeListener(String command, BaseResponseRecord brr) {
		if (command != null) {
			if (command.equals("tickPrices")) sl.receiveTickRecord((STickRecord) brr);
			else if (command.equals("trade")) sl.receiveTradeRecord((STradeRecord) brr);
			else if (command.equals("balance")) sl.receiveBalanceRecord((SBalanceRecord) brr);
			else if (command.equals("tradeStatus")) sl.receiveTradeStatusRecord((STradeStatusRecord) brr);
			else if (command.equals("profit")) sl.receiveProfitRecord((SProfitRecord) brr);
			else if (command.equals("news")) sl.receiveNewsRecord((SNewsRecord) brr);
			else if (command.equals("keepAlive")) sl.receiveKeepAliveRecord((SKeepAliveRecord) brr);
			else if (command.equals("candle")) sl.receiveCandleRecord((SCandleRecord) brr);
		}
	}

	private String readMessageFromStream() throws APICommunicationException {
		StringBuilder sb = new StringBuilder();
		String newline = null;
		String messageString = null;
		boolean readDone = false;
		boolean sockOK = true;
		try {
			while (streamConnected
					&& !readDone
					&& (sockOK = this.checkSocketStateStream(APISocketOperation.READ))
					&& ((newline = streamReader.readLine()) != null)) {
				
				newline = newline.trim();
				
				if ("\n".equals(newline) || "".equals(newline)) {
					messageString = sb.toString();
					readDone = true;
				} else {
					sb.append(newline);
				}
				newline = null;
			}
			if (!sockOK) {
				throw new APICommunicationException("Read error. Socket state invalid.");
			}
		} catch (Exception ex) {
			if (!streamConnected)
				return null;
			else
				throw new APICommunicationException(ex.getMessage());
		}
		return messageString;
	}
}