package pro.xstore.api.streaming;

public class TickPricesStop extends SymbolArgumentRecord {

    public TickPricesStop(String symbol) {
        super(symbol);
    }

	@Override
	protected String getCommand() {
		return "stopTickPrices";
	}
}