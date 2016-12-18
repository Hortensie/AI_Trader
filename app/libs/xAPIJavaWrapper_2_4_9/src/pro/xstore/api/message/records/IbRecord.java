package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class IbRecord implements BaseResponseRecord {

	private String surname;
	private Integer side;
	private Double openPrice;
	private Double nominal;
	private Double closePrice;
	private String symbol;
	private Double volume;
	private String login;
	private Long timestamp;

    @Override
    public void setFieldsFromJSONObject(JSONObject ob) {
    	if (ob != null) {
    		this.symbol = (String) ob.get("symbol");
    		this.surname = (String) ob.get("surname");
    		this.login = (String) ob.get("login");
    		this.openPrice = (Double) ob.get("openPrice");
    		this.nominal = (Double) ob.get("nominal");
    		this.closePrice = (Double) ob.get("closePrice");
    		this.volume = (Double) ob.get("volume");
    		this.timestamp = (Long) ob.get("timestamp");
    		this.side = ((Long) ob.get("side")).intValue();
    	}
    }

	public String getSurname() {
		return surname;
	}

	public Integer getSide() {
		return side;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public Double getNominal() {
		return nominal;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getVolume() {
		return volume;
	}

	public String getLogin() {
		return login;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "IbRecord [surname=" + surname + ", side=" + side
				+ ", openPrice=" + openPrice + ", nominal=" + nominal
				+ ", closePrice=" + closePrice + ", symbol=" + symbol
				+ ", volume=" + volume + ", login=" + login + ", timestamp="
				+ timestamp + "]";
	}
}