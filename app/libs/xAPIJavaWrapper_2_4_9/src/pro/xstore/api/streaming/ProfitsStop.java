package pro.xstore.api.streaming;

public class ProfitsStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopProfits";
	}
}