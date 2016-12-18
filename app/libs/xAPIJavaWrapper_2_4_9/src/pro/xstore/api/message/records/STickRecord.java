package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class STickRecord implements BaseResponseRecord {

	 private Double ask;
	 private Double bid;
	 private Long askVolume;
	 private Long bidVolume;
	 private Double high;
	 private Double low;
	 private double spreadRaw;
	 private double spreadTable;
	 private String symbol;
	 private int quoteId;
	 private int level;
	 private long timestamp;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
        this.ask = (Double) ob.get("ask");
        this.bid = (Double) ob.get("bid");
        
        this.spreadRaw = (Double) ob.get("spreadRaw");
        this.spreadTable = (Double) ob.get("spreadTable");
       
        this.askVolume = (Long) ob.get("askVolume");
        this.bidVolume = (Long) ob.get("bidVolume");
 
        Object obHigh = ob.get("high");
        Double high = obHigh instanceof Double ? (Double) obHigh : (Double) ((Long) obHigh).doubleValue();
        this.high = high == null ? 0 : high;

        Object obLow = ob.get("low");
        Double low = obLow instanceof Double ? (Double) obLow : (Double) ((Long) obLow).doubleValue();
        this.low = low == null ? 0 : low;

        this.symbol = (String) ob.get("symbol");
        this.timestamp = (Long) ob.get("timestamp");
        this.level = ((Long) ob.get("level")).intValue();
        this.quoteId = ((Long) ob.get("quoteId")).intValue();
    }

	public Double getAsk() {
        return ask.doubleValue();
    }

    public Double getBid() {
        return bid.doubleValue();
    }

    public Long getAskVolume() {
        return askVolume;
    }

    public double getSpreadRaw() {
		return spreadRaw;
	}

	public double getSpreadTable() {
		return spreadTable;
	}

	public Long getBidVolume() {
        return bidVolume;
    }

    public int getQuoteId() {
        return quoteId;
    }

	public Double getHigh() {
        return high.doubleValue();
    }

    public Double getLow() {
        return low.doubleValue();
    }

    public String getSymbol() {
        return symbol;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public int getLevel() {
        return level;
    }

	@Override
	public String toString() {
		return "STickRecord [ask=" + ask + ", bid=" + bid + ", askVolume="
				+ askVolume + ", bidVolume=" + bidVolume + ", high=" + high
				+ ", low=" + low + ", spreadRaw=" + spreadRaw
				+ ", spreadTable=" + spreadTable + ", symbol=" + symbol
				+ ", quoteId=" + quoteId + ", level=" + level + ", timestamp="
				+ timestamp + "]";
	}
}