package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class SBalanceRecord implements BaseResponseRecord {

	private double balance;
	private double margin;
	private double equity;
	private double marginLevel;
	private double marginFree;
	private double credit;

	@Override
    public void setFieldsFromJSONObject(JSONObject ob) {
		balance = (Double) ob.get("balance");
		margin = (Double) ob.get("margin");
		credit = (Double) ob.get("credit");
		equity = (Double) ob.get("equity");
		marginLevel = (Double) ob.get("marginLevel");
		marginFree = (Double) ob.get("marginFree");
    }

    public double getBalance() {
		return balance;
	}

	public double getMargin() {
		return margin;
	}
	
	public double getEquity() {
		return equity;
	}

	public double getMarginLevel() {
		return marginLevel;
	}

	public double getMarginFree() {
		return marginFree;
	}
	
    public double getCredit() {
		return credit;
	}

	@Override
	public String toString() {
		return "SBalanceRecord [balance=" + balance + ", margin=" + margin
				+ ", equity=" + equity + ", marginLevel=" + marginLevel
				+ ", marginFree=" + marginFree + ", credit=" + credit + "]";
	}
}