package pro.xstore.api.streaming;

public class CandlesSubscribe extends SymbolArgumentRecord {

	private String streamSessionId;

    public CandlesSubscribe(String symbol, String streamSessionId) {
        super(symbol);
        this.streamSessionId = streamSessionId;
    }

	@Override
	public String getCommand() {
		return "getCandles";
	}

	@Override
	protected String getStreamSessionId() {
		return streamSessionId;
	}
}