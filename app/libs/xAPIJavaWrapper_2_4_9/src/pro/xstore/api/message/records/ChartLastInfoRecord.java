package pro.xstore.api.message.records;

import pro.xstore.api.message.codes.PERIOD_CODE;

import org.json.simple.JSONObject;

public class ChartLastInfoRecord {

    private String symbol;
    private PERIOD_CODE period;
    private long start;

    public ChartLastInfoRecord(String symbol, PERIOD_CODE period, long start) {
        this.symbol = symbol;
        this.period = period;
        this.start = start;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();
        obj.put("symbol", symbol);
        obj.put("period", period.getCode());
        obj.put("start", start);
        return obj;
    }

    @Override
    public String toString() {
        return "ChartLastInfoRecord{" + "symbol=" + symbol + ", period=" + period + ", start=" + start + '}';
    }
}
