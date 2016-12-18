package pro.xstore.api.message.response;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;

public class MarginLevelResponse extends BaseResponse {

    private double balance;
    private double equity;
    private double margin;
    private double margin_free;
    private double margin_level;
    private String currency;
    private double credit;

    public MarginLevelResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject ob = (JSONObject) this.getReturnData();
        this.balance = (Double) ob.get("balance");
        this.credit = (Double) ob.get("credit");
        this.equity = (Double) ob.get("equity");
        this.currency = (String) ob.get("currency");
        this.margin = (Double) ob.get("margin");
        this.margin_free = (Double) ob.get("margin_free");
        this.margin_level = (Double) ob.get("margin_level");
    }

    public double getBalance() {
        return balance;
    }

    public double getEquity() {
        return equity;
    }

    public double getMargin() {
        return margin;
    }

    public double getMargin_free() {
        return margin_free;
    }

    public double getMargin_level() {
        return margin_level;
    }

    public String getCurrency() {
        return currency;
    }

    public double getCredit() {
		return credit;
	}

	@Override
	public String toString() {
		return "MarginLevelResponse [balance=" + balance + ", equity=" + equity
				+ ", margin=" + margin + ", margin_free=" + margin_free
				+ ", margin_level=" + margin_level + ", currency=" + currency
				+ ", credit=" + credit + "]";
	}
}
