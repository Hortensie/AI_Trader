package pro.xstore.api.message.codes;

public class PERIOD_CODE extends CODE {

    public static final PERIOD_CODE PERIOD_M1 = new PERIOD_CODE(1L);
    public static final PERIOD_CODE PERIOD_M5 = new PERIOD_CODE(5L);
    public static final PERIOD_CODE PERIOD_M15 = new PERIOD_CODE(15L);
    public static final PERIOD_CODE PERIOD_M30 = new PERIOD_CODE(30L);
    public static final PERIOD_CODE PERIOD_H1 = new PERIOD_CODE(60L);
    public static final PERIOD_CODE PERIOD_H4 = new PERIOD_CODE(240L);
    public static final PERIOD_CODE PERIOD_D1 = new PERIOD_CODE(1440L);
    public static final PERIOD_CODE PERIOD_W1 = new PERIOD_CODE(10080L);
    public static final PERIOD_CODE PERIOD_MN1 = new PERIOD_CODE(43200L);

    private PERIOD_CODE(long code) {
    	super(code);
    }
}