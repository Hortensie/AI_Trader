package pro.xstore.api.message.response;

import org.json.simple.JSONObject;
import pro.xstore.api.message.codes.REQUEST_STATUS;
import pro.xstore.api.message.error.APIReplyParseException;

public class TradeTransactionStatusResponse extends BaseResponse {

	private Double ask;
    private Double bid;
    private String message;
    private Long order;
    private REQUEST_STATUS requestStatus;
    private String customComment;

    public TradeTransactionStatusResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject ob = (JSONObject) this.getReturnData();
        if(getStatus()) {
	        this.ask = Double.valueOf(ob.get("ask").toString());
	        this.bid = Double.valueOf(ob.get("bid").toString());
	        this.order = (Long) ob.get("order");
	        this.message = (String) ob.get("message");
	        this.customComment = (String) ob.get("customComment");
	        this.requestStatus = new REQUEST_STATUS((Long) ob.get("requestStatus"));
        }
    }

    public Double getAsk() {
		return ask;
	}

	public String getMessage() {
		return message;
	}

	public Double getBid() {
		return bid;
	}

	public Long getOrder() {
		return order;
	}

	public REQUEST_STATUS getRequestStatus() {
		return requestStatus;
	}

	public String getCustomComment() {
		return customComment;
	}

	@Override
	public String toString() {
		return "TradeTransactionStatusResponse [ask=" + ask + ", bid=" + bid
				+ ", message=" + message + ", order=" + order
				+ ", requestStatus=" + requestStatus + ", customComment="
				+ customComment + "]";
	}
}