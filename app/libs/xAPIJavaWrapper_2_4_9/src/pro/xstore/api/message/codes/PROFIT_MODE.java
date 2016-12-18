package pro.xstore.api.message.codes;

public class PROFIT_MODE extends CODE {

	public static final PROFIT_MODE FOREX = new PROFIT_MODE(5L);
	public static final PROFIT_MODE CFD = new PROFIT_MODE(6L);

    public PROFIT_MODE(long code) {
        super(code);
    }
}