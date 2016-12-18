package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class SCandleRecord implements BaseResponseRecord {

    private long ctm;
    private String ctmString;
    private double open;
    private double high;
    private double low;
    private double close;
    private double vol;
    private int quoteId;
    private String symbol;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
    	ctm = (Long) ob.get("ctm");
    	this.ctmString = (String) ob.get("ctmString");
        this.symbol = (String) ob.get("symbol");
        this.quoteId = ((Long) ob.get("quoteId")).intValue();
        this.open = (Double) ob.get("open");
        this.high = (Double) ob.get("high");
        this.low = (Double) ob.get("low");
        this.close = (Double) ob.get("close");
        this.vol = (Double) ob.get("vol");
    }

	public long getCtm() {
		return ctm;
	}

	public String getCtmString() {
		return ctmString;
	}

	public double getOpen() {
		return open;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getClose() {
		return close;
	}

	public double getVol() {
		return vol;
	}

	public int getQuoteId() {
		return quoteId;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "SCandleRecord [ctm=" + ctm + ", ctmString=" + ctmString
				+ ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", vol=" + vol + ", quoteId=" + quoteId
				+ ", symbol=" + symbol + "]";
	}
}