package pro.xstore.api.streaming;

public class NewsSubscribe extends RecordSubscribe {

    public NewsSubscribe(String streamSessionId) {
        super(streamSessionId);
    }

	@Override
	protected String getCommand() {
		return "getNews";
	}
}