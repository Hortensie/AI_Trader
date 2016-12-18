package pro.xstore.api.streaming;

public class TickPricesSubscribe extends SymbolArgumentRecord {

	private String streamSessionId;
	private int minArrivalTime;
	private int maxLevel;

    public TickPricesSubscribe(String symbol, String streamSessionId) {
        super(symbol);
        this.streamSessionId = streamSessionId;
        this.minArrivalTime = 0;
        this.maxLevel = -1;
    }
    
    public TickPricesSubscribe(String symbol, String streamSessionId, int minArrivalTime) {
    	this(symbol, streamSessionId);
    	this.minArrivalTime = minArrivalTime;
    }

    public TickPricesSubscribe(String symbol, String streamSessionId, int minArrivalTime, int maxLevel) {
    	this(symbol, streamSessionId, minArrivalTime);
    	this.maxLevel = maxLevel;
    }

    public TickPricesSubscribe(String symbol, int maxLevel, String streamSessionId) {
    	this(symbol, streamSessionId);
    	this.maxLevel = maxLevel;
    }

	@Override
	protected String getExtraKey2() {
		return this.minArrivalTime == 0 ? "" : "minArrivalTime";
	}

	@Override
	protected int getExtraIntValue2() {
		return minArrivalTime;
	}

	@Override
	protected String getExtraKey3() {
		return this.maxLevel == -1 ? "" : "maxLevel";
	}

	@Override
	protected int getExtraIntValue3() {
		return maxLevel;
	}
	
	@Override
	public String getCommand() {
		return "getTickPrices";
	}

	@Override
	protected String getStreamSessionId() {
		return streamSessionId;
	}
}