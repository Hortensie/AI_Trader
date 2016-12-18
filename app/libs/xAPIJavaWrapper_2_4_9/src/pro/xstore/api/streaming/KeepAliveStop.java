package pro.xstore.api.streaming;

public class KeepAliveStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopKeepAlive";
	}
}