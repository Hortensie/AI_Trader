package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

import pro.xstore.api.message.codes.REQUEST_STATUS;

public class STradeStatusRecord implements BaseResponseRecord {

	private int order;
	private REQUEST_STATUS requestStatus;
	private String message;
	private String customComment;
	private Double price;

	@Override
    public void setFieldsFromJSONObject(JSONObject ob) {
		this.order = ((Long) ob.get("order")).intValue();
		this.price = Double.parseDouble(ob.get("price").toString());
		this.requestStatus = new REQUEST_STATUS((Long) ob.get("requestStatus"));
		this.message = (String) ob.get("message");
		this.customComment = (String) ob.get("customComment");
    }

	public int getOrder() {
		return order;
	}

	public REQUEST_STATUS getRequestStatus() {
		return requestStatus;
	}

	public String getMessage() {
		return message;
	}

	public String getCustomComment() {
		return customComment;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "STradeStatusRecord [order=" + order + ", requestStatus="
				+ requestStatus + ", message=" + message + ", customComment="
				+ customComment + ", price=" + price + "]";
	}
}