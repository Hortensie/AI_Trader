package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

import pro.xstore.api.message.codes.TRADE_OPERATION_CODE;
import pro.xstore.api.message.codes.TRADE_TRANSACTION_TYPE;

public class TradeTransInfoRecord {

    private TRADE_OPERATION_CODE cmd;
    private TRADE_TRANSACTION_TYPE type;
    private Double price;
    private Double sl;
    private Double tp;
    private String symbol;
    private Double volume;
    private Long order;
    private Long expiration;
    private String customComment;

    public TradeTransInfoRecord(TRADE_OPERATION_CODE cmd, TRADE_TRANSACTION_TYPE type, Double price, Double sl, Double tp, String symbol, Double volume, Long order, String customComment, Long expiration) {
        this.cmd = cmd;
        this.type = type;
        this.price = price;
        this.sl = sl;
        this.tp = tp;
        this.symbol = symbol;
        this.volume = volume;
        this.order = order;
        this.expiration = expiration;
        this.customComment = customComment;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("cmd", cmd.getCode());
        obj.put("type", type.getCode());
        obj.put("price", price);
        obj.put("sl", sl);
        obj.put("tp", tp);
        obj.put("symbol", symbol);
        obj.put("volume", volume);
        obj.put("order", order);
        obj.put("customComment", customComment);
        obj.put("expiration", expiration);
        return obj;
    }

    public TRADE_OPERATION_CODE getCmd() {
        return cmd;
    }

    public void setCmd(TRADE_OPERATION_CODE cmd) {
        this.cmd = cmd;
    }

    public TRADE_TRANSACTION_TYPE getType() {
        return type;
    }

    public void setType(TRADE_TRANSACTION_TYPE type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

	public String getCustomComment() {
		return customComment;
	}

	public void setCustomComment(String customComment) {
		this.customComment = customComment;
	}

	@Override
	public String toString() {
		return "TradeTransInfoRecord [cmd=" + cmd + ", type=" + type
				+ ", price=" + price + ", sl=" + sl + ", tp=" + tp
				+ ", symbol=" + symbol + ", volume=" + volume + ", order="
				+ order + ", expiration=" + expiration + ", customComment="
				+ customComment + "]";
	}
}