package pro.xstore.api.message.response;

import pro.xstore.api.message.error.APIReplyParseException;

public class LogoutResponse extends BaseResponse {

    public LogoutResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
    }

    @Override
    public String toString() {
        return "LogoutResponse{" + '}';
    }
}
