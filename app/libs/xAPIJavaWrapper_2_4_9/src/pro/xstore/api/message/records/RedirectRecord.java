package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class RedirectRecord implements BaseResponseRecord {

	private int mainPort;
	private int streamingPort;
	private String address;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
    	this.mainPort = ((Long) ob.get("mainPort")).intValue();
    	this.streamingPort = ((Long) ob.get("streamingPort")).intValue();
    	this.address = (String) ob.get("address");
    }

	public int getMainPort() {
		return mainPort;
	}

	public int getStreamingPort() {
		return streamingPort;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "RedirectRecord [mainPort=" + mainPort + ", streamingPort="
				+ streamingPort + ", address=" + address + "]";
	}
}