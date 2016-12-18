package pro.xstore.api.streaming;

public class CandlesStop extends SymbolArgumentRecord {

    public CandlesStop(String symbol) {
        super(symbol);
    }

	@Override
	protected String getCommand() {
		return "stopCandles";
	}
}