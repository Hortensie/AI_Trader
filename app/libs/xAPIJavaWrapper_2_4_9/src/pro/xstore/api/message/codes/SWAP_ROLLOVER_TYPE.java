package pro.xstore.api.message.codes;

public class SWAP_ROLLOVER_TYPE extends CODE {

	public static final SWAP_ROLLOVER_TYPE MONDAY = new SWAP_ROLLOVER_TYPE(0L);
	public static final SWAP_ROLLOVER_TYPE TUESDAY = new SWAP_ROLLOVER_TYPE(1L);
	public static final SWAP_ROLLOVER_TYPE WEDNSDAY = new SWAP_ROLLOVER_TYPE(2L);
	public static final SWAP_ROLLOVER_TYPE THURSDAY = new SWAP_ROLLOVER_TYPE(3L);
	public static final SWAP_ROLLOVER_TYPE FRIDAY = new SWAP_ROLLOVER_TYPE(4L);

    public SWAP_ROLLOVER_TYPE(long code) {
		super(code);
	}
}