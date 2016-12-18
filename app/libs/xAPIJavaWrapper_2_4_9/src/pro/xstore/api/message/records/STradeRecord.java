package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

import pro.xstore.api.message.codes.STREAMING_TRADE_TYPE;

public class STradeRecord extends WrapperTradeRecord {

	private STREAMING_TRADE_TYPE type;
	private String state;

	private long open_time;

	@Override
	public void setFieldsFromJSONObject(JSONObject ob) {
		super.setFieldsFromJSONObject(ob);
		this.type = new STREAMING_TRADE_TYPE((Long) ob.get("type"));
		this.state = (String) ob.get("state");
		
		this.open_time = (Long) ob.get("open_time");

		Object close_time = ob.get("close_time");
		super.close_time = close_time == null ? null : (Long) close_time;

		Object exp = ob.get("expiration");
		super.expiration = exp == null ? null : (Long) exp;

		super.position = ((Long) ob.get("position")).longValue();

		super.profit = null;
		Object obProfit = ob.get("profit");
		if (obProfit != null) {
			super.profit = (Double) ob.get("profit");
		}

		Object obSl = ob.get("sl");
		Double dSl = 0.0;
		if (obSl != null) {
			dSl = obSl instanceof Double ? (Double) obSl : (Double) ((Long) obSl).doubleValue();
		}
		super.sl = dSl == null ? 0.0 : dSl;

		Object obTp = ob.get("tp");
		Double dTp = 0.0;
		if (obSl != null) {
			dTp = obTp instanceof Double ? (Double) obTp : (Double) ((Long) obTp).doubleValue();
		}
		super.tp = dTp == null ? 0.0 : dTp;
	}

	public STREAMING_TRADE_TYPE getType() {
		return type;
	}

	public String getState() {
		return state;
	}

	public long getOpen_time() {
		return open_time;
	}

	@Override
	public String toString() {
		return "STradeRecord [type=" + type + ", state=" + state
				+ ", open_time=" + open_time + ", toString()="
				+ super.toString() + "]";
	}
}