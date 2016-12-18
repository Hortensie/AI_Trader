package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

abstract class WrapperTradeRecord implements BaseResponseRecord {

	private Double close_price;
	private boolean closed;
	private int cmd;
	private String comment;
	private String customComment;
	private Double commission;
	private long order;
	private long order2;
	private double volume;
	private Double margin_rate;
	private Double open_price;
	private String symbol;
	private Double storage;
	private int digits;

	protected Long close_time;
	protected Long expiration;
	protected long position;
	protected Double profit;
	protected Double sl;
	protected Double tp;

	@Override
	public void setFieldsFromJSONObject(JSONObject ob) {
		this.close_price = Double.parseDouble(ob.get("close_price").toString());
		this.closed = (Boolean) ob.get("closed");
		this.cmd = ((Long) ob.get("cmd")).intValue();
		this.comment = (String) ob.get("comment");
		this.customComment = (String) ob.get("customComment");
		this.commission = Double.parseDouble(ob.get("commission").toString());
		this.order = ((Long) ob.get("order")).longValue();
		this.order2 = ((Long) ob.get("order2")).longValue();
		this.volume = Double.parseDouble(ob.get("volume").toString());
        this.margin_rate = Double.parseDouble(ob.get("margin_rate").toString());
        this.open_price = Double.parseDouble(ob.get("open_price").toString());
        this.symbol = (String) ob.get("symbol");
        this.storage = Double.parseDouble(ob.get("storage").toString());
        this.digits = ((Long) ob.get("digits")).intValue();
	}

	public Double getClose_price() {
		return close_price;
	}

	public int getDigits() {
		return digits;
	}
	
	public boolean isClosed() {
		return closed;
	}

	public int getCmd() {
		return cmd;
	}

	public String getComment() {
		return comment;
	}

	public String getCustomComment() {
		return customComment;
	}

	public Double getCommission() {
		return commission;
	}

	public long getOrder() {
		return order;
	}

	public long getOrder2() {
		return order2;
	}

	public double getVolume() {
		return volume;
	}

	public Double getMargin_rate() {
		return margin_rate;
	}

	public Double getOpen_price() {
		return open_price;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getStorage() {
		return storage;
	}

	public Long getClose_time() {
		return close_time;
	}

	public Long getExpiration() {
		return expiration;
	}

	public long getPosition() {
		return position;
	}

	public Double getProfit() {
		return profit;
	}

	public Double getSl() {
		return sl;
	}

	public Double getTp() {
		return tp;
	}

	@Override
	public String toString() {
		return "WrapperTradeRecord [close_price=" + close_price + ", closed="
				+ closed + ", cmd=" + cmd + ", comment=" + comment
				+ ", customComment=" + customComment + ", commission="
				+ commission + ", order=" + order + ", order2=" + order2
				+ ", volume=" + volume + ", margin_rate=" + margin_rate
				+ ", open_price=" + open_price + ", symbol=" + symbol
				+ ", storage=" + storage + ", digits=" + digits
				+ ", close_time=" + close_time + ", expiration=" + expiration
				+ ", position=" + position + ", profit=" + profit + ", sl="
				+ sl + ", tp=" + tp + "]";
	}
}