package pro.xstore.api.message.codes;

public class TRADE_OPERATION_CODE extends CODE {

    public static final TRADE_OPERATION_CODE BUY = new TRADE_OPERATION_CODE(0L);
    public static final TRADE_OPERATION_CODE SELL = new TRADE_OPERATION_CODE(1L);
    public static final TRADE_OPERATION_CODE BUY_LIMIT = new TRADE_OPERATION_CODE(2L);
    public static final TRADE_OPERATION_CODE SELL_LIMIT = new TRADE_OPERATION_CODE(3L);
    public static final TRADE_OPERATION_CODE BUY_STOP = new TRADE_OPERATION_CODE(4L);
    public static final TRADE_OPERATION_CODE SELL_STOP = new TRADE_OPERATION_CODE(5L);
    public static final TRADE_OPERATION_CODE BALANCE = new TRADE_OPERATION_CODE(6L);
    public static final TRADE_OPERATION_CODE CREDIT = new TRADE_OPERATION_CODE(7L);

    public TRADE_OPERATION_CODE(long code) {
		super(code);
	}
}