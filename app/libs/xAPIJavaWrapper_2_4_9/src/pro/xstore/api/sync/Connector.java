package pro.xstore.api.sync;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

import pro.xstore.api.message.error.APICommunicationException;

public class Connector {
	
	protected Socket socket, streamSocket;
	protected PrintStream writer, streamWriter;
	protected BufferedReader reader, streamReader;
	protected volatile boolean connected, streamConnected;
	protected Server server;

	protected void writeMessageHelper(String message, boolean isStream) throws APICommunicationException {
		APISocketOperation arg = APISocketOperation.WRITE;
		boolean checkResult = isStream ? checkSocketStateStream(arg) : checkSocketState(arg);
		@SuppressWarnings("resource")
		PrintStream tmpWriter = isStream ? streamWriter : writer;
		if(checkResult) {
			tmpWriter.print(message);
			if (tmpWriter.checkError()) {
				if (isStream) {
					streamConnected = false;
				} else {
					connected = false;
				}
				throw new APICommunicationException("Write error.");
			}
		} else {
			throw new APICommunicationException("Write error. Socket state invalid.");
		}
	}

	public boolean isStreamConnected() {
		return streamConnected;
	}
	
	protected boolean checkSocketState(APISocketOperation op) {
		final boolean state = checkSocketStateHelper(op, false);
		if (!state) {
			connected = false;
		}
		return state;
	}

	protected boolean checkSocketStateStream(APISocketOperation op) {
		final boolean streamingState = checkSocketStateHelper(op, true);
		if (!streamingState) {
			streamConnected = false;
		}
		return streamingState;
	}
	
	private boolean checkSocketStateHelper(APISocketOperation op, boolean isStream) {
		@SuppressWarnings("resource")
		Socket soc = isStream ? streamSocket : socket;
		switch (op) {
			case CLOSE:
				return soc.isConnected();
				
			case READ:
				if (!soc.isConnected()) {
					return false;
				}
				return !soc.isInputShutdown();
				
			case WRITE:
				if (!soc.isConnected()) {
					return false;
				}
				return !soc.isOutputShutdown();
		}
		return false;
	}
}