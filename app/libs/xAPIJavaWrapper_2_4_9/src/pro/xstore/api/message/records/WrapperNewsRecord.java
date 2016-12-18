package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

abstract class WrapperNewsRecord implements BaseResponseRecord {

	protected String body; //may not be present
	protected String title;
	protected String key;
	protected long time;

	@Override
	public void setFieldsFromJSONObject(JSONObject ob) {
		this.key = (String) ob.get("key");
		this.time = (Long) ob.get("time");
		this.title = (String) ob.get("title");
		this.body = (String) ob.get("body");
	}

	public String getKey() {
		return key;
	}

	public long getTime() {
		return time;
	}

	public String getBody() {
		return body;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "WrapperNewsRecord [body=" + body + ", title=" + title
				+ ", key=" + key + ", time=" + time + "]";
	}
}