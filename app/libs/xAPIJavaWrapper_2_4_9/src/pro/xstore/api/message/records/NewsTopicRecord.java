package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class NewsTopicRecord extends WrapperNewsRecord {

	private String timeString;
	private int bodylen;

	@Override
	public void setFieldsFromJSONObject(JSONObject ob) {
		super.setFieldsFromJSONObject(ob);
		this.timeString = (String) ob.get("timeString");
		this.bodylen = ((Long) ob.get("bodylen")).intValue();
	}

	public String getTimeString() {
		return timeString;
	}

	public int getBodylen() {
		return bodylen;
	}

	@Override
	public String toString() {
		return "NewsTopicRecord [timeString=" + timeString + ", bodylen="
				+ bodylen + ", toString()=" + super.toString() + "]";
	}
}