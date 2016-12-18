package pro.xstore.api.streaming;

public class TradeStatusRecordsSubscribe extends RecordSubscribe {

    public TradeStatusRecordsSubscribe(String streamSessionId) {
        super(streamSessionId);
    }

	@Override
	public String getCommand() {
		return "getTradeStatus";
	}
}