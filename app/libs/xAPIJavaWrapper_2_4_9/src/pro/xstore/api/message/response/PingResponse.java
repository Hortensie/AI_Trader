package pro.xstore.api.message.response;

import pro.xstore.api.message.error.APIReplyParseException;

public class PingResponse extends BaseResponse {

    public PingResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
    }

	@Override
	public String toString() {
		return "PingResponse ["+ super.getStatus() + "]";
	}
}