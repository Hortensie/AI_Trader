package pro.xstore.api.streaming;

import org.json.simple.JSONObject;

public abstract class StreamingCommandRecord {

    @SuppressWarnings("unchecked")
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("command", this.getCommand());
        
        String extraKey = this.getExtraKey();
        if (!extraKey.equals("")) {
        	obj.put(extraKey, getExtraValue());
        }
        
        String extraKey2 = this.getExtraKey2();
        if (!extraKey2.equals("")) {
        	obj.put(extraKey2, getExtraIntValue2());
        }

        String extraKey3 = this.getExtraKey3();
        if (!extraKey3.equals("")) {
        	obj.put(extraKey3, getExtraIntValue3());
        }

        String streamSessionId = getStreamSessionId();
        if (!streamSessionId.equals("")) {
        	obj.put("streamSessionId", streamSessionId);
        }
        return obj.toJSONString();
    }

    protected String getExtraKey() {
    	return "";
    }

    protected String getExtraValue() {
    	return "";
    }

    protected String getExtraKey2() {
    	return "";
    }

    protected int getExtraIntValue2() {
    	return 0;
    }

    protected String getExtraKey3() {
    	return "";
    }

    protected int getExtraIntValue3() {
    	return 0;
    }

    protected String getStreamSessionId() {
    	return "";
    }

    protected abstract String getCommand();
}