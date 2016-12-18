package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

import pro.xstore.api.message.codes.MARGIN_MODE;
import pro.xstore.api.message.codes.PROFIT_MODE;
import pro.xstore.api.message.codes.SWAP_ROLLOVER_TYPE;
import pro.xstore.api.message.codes.SWAP_TYPE;

public class SymbolRecord implements BaseResponseRecord {

    private double ask;
    private double bid;
    private String currency;
    private String currencyProfit;
    private String description;
    private int instantMaxVolume;
    private double high;
    private double low;
    private String symbol;
    private long time;
    private int type;
    private String groupName;
    private String categoryName;
    private boolean longOnly;
    private Long starting;
    private Long expiration;
    private int stepRuleId;
    private int stopsLevel;
    private double lotMax;
    private double lotMin;
    private double lotStep;
    private int precision;
    private Long contractSize;
    private Long initialMargin;
    private double marginHedged;
    private boolean marginHedgedStrong;
    private Long marginMaintenance;
    private MARGIN_MODE marginMode;
    private double percentage;
    private PROFIT_MODE profitMode;
	private double spreadRaw;
	private double spreadTable;
    private boolean swapEnable;
    private double swapLong;
    private double swapShort;
    private SWAP_TYPE swapType;
    private SWAP_ROLLOVER_TYPE swapRollover;
    private double tickSize;
    private double tickValue;
    private int quoteId;
    private String timeString;
    private double leverage;
    private boolean currencyPair;

    @Override
    public void setFieldsFromJSONObject(JSONObject e) {
    	if (e != null) {
	        this.setAsk((Double) e.get("ask"));
	        this.setBid((Double) e.get("bid"));
	        this.setCategoryName((String) e.get("categoryName"));
	        this.setCurrency((String) e.get("currency"));
	        this.setDescription((String) e.get("description"));
	        this.setExpiration((Long) e.get("expiration"));
	        this.setGroupName((String) e.get("groupName"));
	        this.setHigh((Double) e.get("high"));
	        this.setInstantMaxVolume(((Long) e.get("instantMaxVolume")).intValue());
	        this.setLongOnly((Boolean) e.get("longOnly"));
	        this.setLotMax((Double) e.get("lotMax"));
	        this.setLotMin((Double) e.get("lotMin"));
	        this.setLow((Double) e.get("low"));
	        this.setPrecision(((Long) e.get("precision")).intValue());
	        this.setStepRuleId(((Long) e.get("stepRuleId")).intValue());
	        this.setStarting((Long) e.get("starting"));
	        this.setStopsLevel(((Long) e.get("stopsLevel")).intValue());
	        this.setSymbol((String) e.get("symbol"));
	        this.setQuoteId(((Long) e.get("quoteId")).intValue());
	        this.setCurrencyProfit((String) e.get("currencyProfit"));
	        this.setTime((Long) e.get("time"));
	        this.setTimeString((String) e.get("timeString"));
	        this.setType(((Long) e.get("type")).intValue());
	        
	        if (e.get("contractSize") != null) {
	        	this.setContractSize(((Long) e.get("contractSize")).longValue());
	        }
	        if (e.get("initialMargin") != null) {
	        	this.setInitialMargin(((Long) e.get("initialMargin")).longValue());
	        }
	        this.setLotStep((Double) e.get("lotStep"));
	        if (e.get("marginHedged") != null) {
	        	this.setMarginHedged(Double.valueOf(e.get("marginHedged").toString()));
	        }
	        this.setMarginHedgedStrong((Boolean) e.get("marginHedgedStrong"));
	        if (e.get("marginMaintenance") != null) {
	        	this.setMarginMaintenance(((Long) e.get("marginMaintenance")).longValue());
	        }
	        this.setMarginMode(new MARGIN_MODE((Long) e.get("marginMode")));
	        this.setPercentage((Double) e.get("percentage"));
	        this.setProfitMode(new PROFIT_MODE((Long) e.get("profitMode")));
	        this.setSwapEnable((Boolean) e.get("swapEnable"));
	        this.setSwapLong((Double) e.get("swapLong"));
	        this.setSwapShort((Double) e.get("swapShort"));
	        this.setSwapType(new SWAP_TYPE((Long) e.get("swapType")));
	        this.setSwapRollover(new SWAP_ROLLOVER_TYPE((Long) e.get("swap_rollover3days")));
	        this.setTickSize((Double) e.get("tickSize"));
	        this.setTickValue((Double) e.get("tickValue"));
	        this.currencyPair = (Boolean) e.get("currencyPair");
	        this.leverage = (Double) e.get("leverage");
	        this.spreadRaw = (Double) e.get("spreadRaw");
	        this.spreadTable = (Double) e.get("spreadTable");
    	}
    }

    public double getLeverage() {
		return leverage;
	}

	public boolean isCurrencyPair() {
		return currencyPair;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getCurrencyProfit() {
		return currencyProfit;
	}

	public int getQuoteId() {
		return quoteId;
	}

    public double getSpreadRaw() {
		return spreadRaw;
	}

	public double getSpreadTable() {
		return spreadTable;
	}

	public void setCurrencyProfit(String currencyProfit) {
		this.currencyProfit = currencyProfit;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

	public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstantMaxVolume() {
        return instantMaxVolume;
    }

    public void setInstantMaxVolume(int instantMaxVolume) {
        this.instantMaxVolume = instantMaxVolume;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isLongOnly() {
        return longOnly;
    }

    public void setLongOnly(boolean longOnly) {
        this.longOnly = longOnly;
    }

    public Long getStarting() {
        return starting;
    }

    public void setStarting(Long starting) {
        this.starting = starting;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public int getStopsLevel() {
        return stopsLevel;
    }

    public void setStopsLevel(int stopsLevel) {
        this.stopsLevel = stopsLevel;
    }

    public Double getLotMax() {
        return lotMax;
    }

    public void setLotMax(Double lotMax) {
        this.lotMax = lotMax;
    }

    public Double getLotMin() {
        return lotMin;
    }

    public void setLotMin(Double lotMin) {
        this.lotMin = lotMin;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Boolean getLongOnly() {
		return longOnly;
	}

	public void setLongOnly(Boolean longOnly) {
		this.longOnly = longOnly;
	}

	public Long getContractSize() {
		return contractSize;
	}

	public void setContractSize(Long contractSize) {
		this.contractSize = contractSize;
	}

	public Long getInitialMargin() {
		return initialMargin;
	}

	public void setInitialMargin(Long initialMargin) {
		this.initialMargin = initialMargin;
	}

	public Double getLotStep() {
		return lotStep;
	}

	public void setLotStep(Double lotStep) {
		this.lotStep = lotStep;
	}

	public Double getMarginHedged() {
		return marginHedged;
	}

	public void setMarginHedged(Double marginHedged) {
		this.marginHedged = marginHedged;
	}

	public Boolean getMarginHedgedStrong() {
		return marginHedgedStrong;
	}

	public void setMarginHedgedStrong(Boolean marginHedgedStrong) {
		this.marginHedgedStrong = marginHedgedStrong;
	}

	public Long getMarginMaintenance() {
		return marginMaintenance;
	}

	public void setMarginMaintenance(Long marginMaintenance) {
		this.marginMaintenance = marginMaintenance;
	}

	public MARGIN_MODE getMarginMode() {
		return marginMode;
	}

	public void setMarginMode(MARGIN_MODE marginMode) {
		this.marginMode = marginMode;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public PROFIT_MODE getProfitMode() {
		return profitMode;
	}

	public void setProfitMode(PROFIT_MODE profitMode) {
		this.profitMode = profitMode;
	}

	public boolean isSwapEnable() {
		return swapEnable;
	}

	public void setSwapEnable(boolean swapEnable) {
		this.swapEnable = swapEnable;
	}

	public Double getSwapLong() {
		return swapLong;
	}

	public void setSwapLong(Double swapLong) {
		this.swapLong = swapLong;
	}

	public Double getSwapShort() {
		return swapShort;
	}

	public void setSwapShort(Double swapShort) {
		this.swapShort = swapShort;
	}

	public SWAP_TYPE getSwapType() {
		return swapType;
	}

	public void setSwapType(SWAP_TYPE swapType) {
		this.swapType = swapType;
	}

	public SWAP_ROLLOVER_TYPE getSwap_rollover() {
		return swapRollover;
	}

	public void setSwapRollover(SWAP_ROLLOVER_TYPE swap_rollover) {
		this.swapRollover = swap_rollover;
	}

	public double getTickSize() {
		return tickSize;
	}

	public void setTickSize(double tickSize) {
		this.tickSize = tickSize;
	}

	public double getTickValue() {
		return tickValue;
	}

	public void setTickValue(double tickValue) {
		this.tickValue = tickValue;
	}

	@Override
	public String toString() {
		return "SymbolRecord [ask=" + ask + ", bid=" + bid + ", currency="
				+ currency + ", currencyProfit=" + currencyProfit
				+ ", description=" + description + ", instantMaxVolume="
				+ instantMaxVolume + ", high=" + high + ", low=" + low
				+ ", symbol=" + symbol + ", time=" + time + ", type=" + type
				+ ", groupName=" + groupName + ", categoryName=" + categoryName
				+ ", longOnly=" + longOnly + ", starting=" + starting
				+ ", expiration=" + expiration + ", stopsLevel=" + stopsLevel
				+ ", lotMax=" + lotMax + ", lotMin=" + lotMin + ", lotStep="
				+ lotStep + ", precision=" + precision + ", contractSize="
				+ contractSize + ", initialMargin=" + initialMargin
				+ ", marginHedged=" + marginHedged + ", marginHedgedStrong="
				+ marginHedgedStrong + ", marginMaintenance="
				+ marginMaintenance + ", marginMode=" + marginMode
				+ ", percentage=" + percentage + ", profitMode=" + profitMode
				+ ", swapEnable=" + swapEnable + ", swapLong=" + swapLong
				+ ", swapShort=" + swapShort + ", swapType=" + swapType
				+ ", swapRollover=" + swapRollover + ", tickSize=" + tickSize
				+ ", tickValue=" + tickValue + ", quoteId=" + quoteId
				+ ", timeString=" + timeString + ", spreadRaw=" + spreadRaw
				+ ", spreadTable=" + spreadTable + ", leverage=" + leverage
				+ ", stepRuleId=" + stepRuleId
				+ ", currencyPair=" + currencyPair + "]";
	}

    public int getStepRuleId() {
        return stepRuleId;
    }

    public void setStepRuleId(int stepRuleId) {
        this.stepRuleId = stepRuleId;
    }
}