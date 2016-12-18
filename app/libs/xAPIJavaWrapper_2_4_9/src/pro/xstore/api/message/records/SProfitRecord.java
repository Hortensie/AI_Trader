package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class SProfitRecord implements BaseResponseRecord {
	
	private long order;
	private long order2;
	private long position;
	private double profit;

	public long getOrder() {
		return order;
	}

	public long getOrder2() {
		return order2;
	}

	public long getPosition() {
		return position;
	}

	public double getProfit() {
		return profit;
	}

	@Override
	public String toString() {
		return "SProfitRecord [order=" + order + ", order2=" + order2
				+ ", position=" + position + ", profit=" + profit + "]";
	}

	@Override
	public void setFieldsFromJSONObject(JSONObject e) {
        order = ((Long) e.get("order")).longValue();
        order2 = ((Long) e.get("order2")).longValue();
        position = ((Long) e.get("position")).longValue();
        profit = (Double) e.get("profit");
	}
}