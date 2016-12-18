package pro.xstore.api.message.codes;

public class MARGIN_MODE extends CODE {

	public static final MARGIN_MODE FOREX = new MARGIN_MODE(101L);
	public static final MARGIN_MODE CFD_LEVERAGED = new MARGIN_MODE(102L);
	public static final MARGIN_MODE CFD = new MARGIN_MODE(103L);

    public MARGIN_MODE(long code){
        super(code);
    }
}