package pro.xstore.api.message.error;

public class APICommunicationException extends Exception {

	private static final long serialVersionUID = -4160249141921590790L;

	/**
     * Creates a new instance of
     * <code>APICommunicationException</code> without detail message.
     */
    public APICommunicationException() {}

    /**
     * Constructs an instance of
     * <code>APICommunicationException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public APICommunicationException(String msg) {
        super(msg);
    }
}