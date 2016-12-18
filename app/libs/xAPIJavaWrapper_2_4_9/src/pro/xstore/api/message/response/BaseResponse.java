package pro.xstore.api.message.response;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.error.ERR_CODE;
import pro.xstore.api.sync.SyncAPIConnector;

public class BaseResponse {

    private Boolean status;
    private String errorDescr;
    private ERR_CODE errCode;
    private Object returnData;
    private JSONObject redirectJson;
    private String streamSessionId;

    public BaseResponse(String body) throws APIReplyParseException, APIErrorResponse {
        JSONObject ob;
        try {
            ob = (JSONObject) JSONValue.parseWithException(body);
        } catch (Exception ex) {
        	String resp = "JSON Parse error: " + ex.getMessage() + "\nWhile trying to parse: " + body;
        	if (SyncAPIConnector.throwOnError) {
        		throw new APIReplyParseException(resp);
        	}
        	else
        	{
        		System.err.println(resp);
        	}
        	ob = null;
        }
        if (ob == null) {
            throw new APIReplyParseException("JSON Parse exception: " + body);
        } else {
            this.status = (Boolean) ob.get("status");
            this.errCode = new ERR_CODE((String)ob.get("errorCode"));
            this.errorDescr = (String) ob.get("errorDescr");
            this.returnData = (JSONAware) ob.get("returnData");
            this.streamSessionId = (String) ob.get("streamSessionId");

            if (this.status==null) {
                String resp = "JSON Parse error: " + "\"status\" is null!";
                if(SyncAPIConnector.throwOnError) {
                	throw new APIReplyParseException(resp);
                } else
                {
                	System.err.println(resp);
                }
            }
            
            try {
            	this.redirectJson = (JSONObject) ob.get("redirect");
            } catch (Exception ignore) {
            	this.redirectJson = null;
            }
            
            if (!this.status && SyncAPIConnector.throwOnError && redirectJson == null) {
                throw new APIErrorResponse(errCode, errorDescr, body);
            }
        }
    }

    public Object getReturnData() {
        return returnData;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getErrorDescr() {
		return errorDescr;
	}

	public ERR_CODE getErrCode() {
		return errCode;
	}

	public String getStreamSessionId() {
        return streamSessionId;
    }

	public JSONObject getRedirectJson() {
		return redirectJson;
	}

	@Override
	public String toString() {
		return "BaseResponse [status=" + status + ", errorDescr=" + errorDescr
				+ ", errCode=" + errCode + ", returnData=" + returnData
				+ ", redirectJson=" + redirectJson + ", streamSessionId="
				+ streamSessionId + "]";
	}
}