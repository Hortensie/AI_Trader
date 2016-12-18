package pro.xstore.api.streaming;

public class BalanceSubscribe extends RecordSubscribe {

    public BalanceSubscribe(String streamSessionId) {
    	super(streamSessionId);
    }

	@Override
	public String getCommand() {
		return "getBalance";
	}
}