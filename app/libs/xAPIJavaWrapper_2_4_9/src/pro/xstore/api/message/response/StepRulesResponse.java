package pro.xstore.api.message.response;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.StepRecord;

public class StepRulesResponse extends BaseResponse {

    private List<Integer> ids = new LinkedList<Integer>();
    private List<String> names = new LinkedList<String>();
    private List<List<StepRecord>> stepRecords = new LinkedList<List<StepRecord>>();

    @SuppressWarnings("rawtypes")
    public StepRulesResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONArray returnDataArray = (JSONArray) this.getReturnData();
        Iterator it1 = returnDataArray.iterator();
        while (it1.hasNext()) {
        	JSONObject ob = (JSONObject) it1.next();
        	int id = ((Long) ob.get("id")).intValue();
        	ids.add(id);

        	String name = (String) ob.get("name");
        	names.add(name);

        	List<StepRecord> stepsList = new LinkedList<StepRecord>();
            JSONArray stepsArray = (JSONArray) ob.get("steps");
            Iterator it2 = stepsArray.iterator();
            while (it2.hasNext()) {
            	JSONObject e = (JSONObject) it2.next();
            	StepRecord rec = new StepRecord();
            	rec.setFieldsFromJSONObject(e);
            	stepsList.add(rec);
            }
            stepRecords.add(stepsList);
        }
    }
    
	public List<Integer> getIds() {
		return ids;
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public List<List<StepRecord>> getStepRecords() {
		return stepRecords;
	}

	@Override
	public String toString() {
		return "StepRulesResponse [ids=" + ids + ", names=" + names
				+ ", stepRecords=" + stepRecords + "]";
	}
}