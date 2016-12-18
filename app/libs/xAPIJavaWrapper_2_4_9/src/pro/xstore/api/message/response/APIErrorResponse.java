package pro.xstore.api.message.response;

import pro.xstore.api.message.error.ERR_CODE;

public class APIErrorResponse extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ERR_CODE code;
    private String errDesc;
    private String msg;

    /**
     * Constructs an instance of
     * <code>APIErrorResponse</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public APIErrorResponse(ERR_CODE code, String errDesc, String msg) {
        super(msg);
        this.code = code;
        this.errDesc = errDesc;
        this.msg = msg;
    }

    public ERR_CODE getCode() {
		return code;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public String getMsg() {
		return msg;
	}

	@Override
    public String getMessage() {
        return "ERR_CODE = " + code.stringValue() + "ERR_DESC = " + errDesc + "\n" +msg + "\n" +  super.getMessage();
    }

    @Override
    public String toString() {
        return "APIErrorResponse{" + "code=" + code + ", errDesc=" + errDesc + ", msg=" + msg + '}';
    }
}