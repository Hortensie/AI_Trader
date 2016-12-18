package pro.xstore.api.streaming;

public abstract class RecordSubscribe extends StreamingCommandRecord {

    private String streamSessionId;

    public RecordSubscribe(String streamSessionId) {
        this.streamSessionId = streamSessionId;
    }

	@Override
	protected String getStreamSessionId() {
		return streamSessionId;
	}

	protected abstract String getCommand();
}