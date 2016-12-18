package pro.xstore.api.message.records;

import pro.xstore.api.message.codes.PERIOD_CODE;

import org.json.simple.JSONObject;

public class ChartRangeInfoRecord {

    private String symbol;
    private PERIOD_CODE period;
    private long start;
    private long end;
    private long ticks;

    public ChartRangeInfoRecord(String symbol, PERIOD_CODE period, long start, long end, long ticks) {
        this.symbol = symbol;
        this.period = period;
        this.start = start;
        this.end = end;
        this.ticks = ticks;
    }

    public ChartRangeInfoRecord(String symbol, PERIOD_CODE period, long start, long end) {
        this.symbol = symbol;
        this.period = period;
        this.start = start;
        this.end = end;
        
        this.ticks = 0L;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("symbol", symbol);
        obj.put("period", period.getCode());
        obj.put("start", start);
        obj.put("end", end);
        obj.put("ticks", ticks);
        return obj;
    }

    @Override
    public String toString() {
        return "ChartRangeInfoRecord{" + "symbol=" + symbol + ", period=" + period + ", start=" + start + ", end=" + end + ", ticks=" + ticks + '}';
    }
}
