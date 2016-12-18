package pro.xstore.api.message.response;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.RateInfoRecord;

public class ChartResponse extends BaseResponse {

    private int digits;
    private List<RateInfoRecord> rateInfos = new LinkedList<RateInfoRecord>();

    @SuppressWarnings("rawtypes")
    public ChartResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject rd = (JSONObject) this.getReturnData();
        if (rd != null) {
	        this.digits = ((Long) rd.get("digits")).intValue();
	        JSONArray arr = (JSONArray) rd.get("rateInfos");
	        for (Iterator it = arr.iterator(); it.hasNext();) {
	            JSONObject e = (JSONObject) it.next();
	            RateInfoRecord record = new RateInfoRecord();
	            record.setFieldsFromJSONObject(e);
	            this.rateInfos.add(record);
	        }
        }
    }

    public int getDigits() {
        return digits;
    }

    public List<RateInfoRecord> getRateInfos() {
        return rateInfos;
    }

    @Override
    public String toString() {
    	String response = "ChartResponse{" + "digits=" + digits + ", rateInfos=[";
    	for (RateInfoRecord ri : rateInfos) {
    		response += ri.toString() + ",";
    	}
    	response += "]}";
        return response;
    }
}