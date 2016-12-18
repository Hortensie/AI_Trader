package pro.xstore.api.message.error;

public class APIReplyParseException extends Exception {

	private static final long serialVersionUID = 6518944426920316999L;

	/**
     * Creates a new instance of
     * <code>APIReplyParseException</code> without detail message.
     */
    public APIReplyParseException() {}

    /**
     * Constructs an instance of
     * <code>APIReplyParseException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public APIReplyParseException(String msg) {
        super(msg);
    }
}