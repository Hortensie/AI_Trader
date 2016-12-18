package pro.xstore.api.streaming;

public class TradeRecordsSubscribe extends RecordSubscribe {

    public TradeRecordsSubscribe(String streamSessionId) {
        super(streamSessionId);
    }

	@Override
	protected String getCommand() {
		return "getTrades";
	}
}