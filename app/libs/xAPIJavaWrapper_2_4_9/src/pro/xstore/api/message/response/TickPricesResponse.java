package pro.xstore.api.message.response;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.TickRecord;

public class TickPricesResponse extends BaseResponse {
	
    private List<TickRecord> ticks = new LinkedList<TickRecord>();
    
    @SuppressWarnings("rawtypes")
    public TickPricesResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject ob = (JSONObject) this.getReturnData();
        JSONArray arr = (JSONArray) ob.get("quotations");
        for (Iterator it = arr.iterator(); it.hasNext();) {
            JSONObject e = (JSONObject) it.next();
            TickRecord record = new TickRecord();
            record.setFieldsFromJSONObject(e);
            ticks.add(record);
        }
    }

    public List<TickRecord> getTicks() {
        return ticks;
    }

    @Override
    public String toString() {
        return "TickPricesResponse{ticks=" + ticks + '}';
    }
}