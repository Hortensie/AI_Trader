package pro.xstore.api.message.response;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;

public class TradeTransactionResponse extends BaseResponse {

    private long order;

    public TradeTransactionResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject ob = (JSONObject) this.getReturnData();
        if (getStatus()) {
        	this.order = (Long) ob.get("order");
        }
    }

    public long getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "TradeTransactionResponse{" + "order=" + order + '}';
    }
}