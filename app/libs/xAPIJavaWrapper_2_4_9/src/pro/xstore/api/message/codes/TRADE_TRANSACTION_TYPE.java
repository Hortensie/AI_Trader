package pro.xstore.api.message.codes;

public class TRADE_TRANSACTION_TYPE extends CODE {

    public static final TRADE_TRANSACTION_TYPE OPEN = new TRADE_TRANSACTION_TYPE(0L);
    public static final TRADE_TRANSACTION_TYPE CLOSE = new TRADE_TRANSACTION_TYPE(2L);
    public static final TRADE_TRANSACTION_TYPE MODIFY = new TRADE_TRANSACTION_TYPE(3L);
    public static final TRADE_TRANSACTION_TYPE DELETE = new TRADE_TRANSACTION_TYPE(4L);

    public TRADE_TRANSACTION_TYPE(long code) {
		super(code);
	}
}