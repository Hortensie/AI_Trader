package pro.xstore.api.streaming;

public class NewsStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopNews";
	}
}