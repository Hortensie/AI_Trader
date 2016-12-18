package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class StepRecord implements BaseResponseRecord {

    private double fromValue;
    private double step;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
        this.fromValue = (Double) ob.get("fromValue");
    	this.step = (Double) ob.get("step");
    }

	public double getFromValue() {
		return fromValue;
	}

	public double getStep() {
		return step;
	}

	@Override
	public String toString() {
		return "StepRecord [fromValue=" + fromValue + ", step=" + step + "]";
	}
}