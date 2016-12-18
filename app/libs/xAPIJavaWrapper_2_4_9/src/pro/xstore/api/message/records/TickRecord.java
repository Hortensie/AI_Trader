package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class TickRecord implements BaseResponseRecord {

    private Double ask;
    private Double bid;
    private Long askVolume;
    private Long bidVolume;
    private Double high;
    private Double low;
    private String symbol;
    private Double timestamp;
    private int level;
	private double spreadRaw;
	private double spreadTable;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
        this.ask = (Double) ob.get("ask");
        this.bid = (Double) ob.get("bid");
        this.askVolume = (Long) ob.get("askVolume");
        if (askVolume == null) {
            this.askVolume = 0L;
        }
        this.bidVolume = (Long) ob.get("bidVolume");
        if (bidVolume == null) {
            this.bidVolume = 0L;
        }

        Object obHigh = ob.get("high");
        Double high = obHigh instanceof Double ? (Double) obHigh : (Double) ((Long) obHigh).doubleValue();
        this.high = high == null ? 0 : high;

        Object obLow = ob.get("low");
        Double low = obHigh instanceof Double ? (Double) obLow : (Double) ((Long) obLow).doubleValue();
        this.low = low == null ? 0 : low;

        this.symbol = (String) ob.get("symbol");
        
        this.timestamp = ((Long) ob.get("timestamp")).doubleValue();
        
        this.level = ((Long) ob.get("level")).intValue();
        
        this.spreadRaw = (Double) ob.get("spreadRaw");
        this.spreadTable = (Double) ob.get("spreadTable");
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
        return timestamp.longValue();
    }

    public int getLevel() {
        return level;
    }

	@Override
	public String toString() {
		return "TickRecord [ask=" + ask + ", bid=" + bid + ", askVolume="
				+ askVolume + ", bidVolume=" + bidVolume + ", high=" + high
				+ ", low=" + low + ", symbol=" + symbol + ", timestamp="
				+ timestamp + ", level=" + level + ", spreadRaw=" + spreadRaw
				+ ", spreadTable=" + spreadTable + "]";
	}

}