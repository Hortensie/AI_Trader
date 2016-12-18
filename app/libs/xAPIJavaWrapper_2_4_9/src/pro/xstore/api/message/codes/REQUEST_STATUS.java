package pro.xstore.api.message.codes;

public class REQUEST_STATUS extends CODE {

    public static final REQUEST_STATUS ERROR = new REQUEST_STATUS(0L);
    public static final REQUEST_STATUS PENDING = new REQUEST_STATUS(1L);
    public static final REQUEST_STATUS ACCEPTED = new REQUEST_STATUS(3L);
    public static final REQUEST_STATUS REJECTED = new REQUEST_STATUS(4L);

	public REQUEST_STATUS(long code) {
		super(code);
	}
}