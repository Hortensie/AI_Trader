package pro.xstore.api.message.codes;

public class SWAP_TYPE extends CODE {

	public static final SWAP_TYPE SWAP_BY_POINTS = new SWAP_TYPE(0L);
	public static final SWAP_TYPE SWAP_BY_DOLLARS = new SWAP_TYPE(1L);
	public static final SWAP_TYPE SWAP_BY_INTEREST = new SWAP_TYPE(2L);
	public static final SWAP_TYPE SWAP_BY_MARGIN_CURRENCY = new SWAP_TYPE(3L);

    public SWAP_TYPE(long code) {
		super(code);
	}
}