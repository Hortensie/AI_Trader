package pro.xstore.api.sync;

public class Server {

    private String address;
    private int mainPort;
    private int streamingPort;
    private String description;
    private boolean secure;
    private boolean partOfXapiList;

	private Server(String address, int mainPort, int streamingPort, boolean secure, String description)
    {
        this.address = address;
        this.mainPort = mainPort;
        this.streamingPort = streamingPort;
        this.secure = secure;
        this.description = description;
    }

	public Server(String address, int mainPort, int streamingPort, boolean secure, String description, boolean partOfXapiList) {
		this(address, mainPort, streamingPort, secure, description);
		this.partOfXapiList = partOfXapiList;
	}

    /**
     * Returns address of server
     */
    public String getAddress() {
		return address;
	}

    /**
     * Returns main port of the server
     */
	public int getMainPort() {
		return mainPort;
	}

	/**
	 * Returns streaming port of the server
	 */
	public int getStreamingPort() {
		return streamingPort;
	}

	/**
	 * Returns short description of the server
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns info if using SSL is necessary
	 */
	public boolean isSecure() {
		return secure;
	}

	public boolean isPartOfXapiList() {
		return partOfXapiList;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Server [address=" + address + ", mainPort=" + mainPort
				+ ", streamingPort=" + streamingPort + ", description="
				+ description + ", secure=" + secure + ", partOfXapiList="
				+ partOfXapiList + "]";
	}
}