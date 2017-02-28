package com.hortensie.ai_trader.xAPI;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.codes.MARGIN_MODE;
import pro.xstore.api.message.codes.PROFIT_MODE;
import pro.xstore.api.message.codes.SWAP_ROLLOVER_TYPE;
import pro.xstore.api.message.codes.SWAP_TYPE;
import pro.xstore.api.message.records.*;

/**
 * Created by szczesny on 2017-02-22.
 */

public class ListSymbolRecord {

        public double ask;
        public double bid;
        public String currency;
        public String currencyProfit;
        public String description;
        public int instantMaxVolume;
        public double high;
        public double low;
        public String symbol;
        public long time;
        public int type;
        public String groupName;
        public String categoryName;
        public boolean longOnly;
        public Long starting;
        public Long expiration;
        public int stepRuleId;
        public int stopsLevel;
        public double lotMax;
        public double lotMin;
        public double lotStep;
        public int precision;
        public Long contractSize;
        public Long initialMargin;
        public double marginHedged;
        public boolean marginHedgedStrong;
        public Long marginMaintenance;
        public MARGIN_MODE marginMode;
        public double percentage;
        public PROFIT_MODE profitMode;
        public double spreadRaw;
        public double spreadTable;
        public boolean swapEnable;
        public double swapLong;
        public double swapShort;
        public SWAP_TYPE swapType;
        public SWAP_ROLLOVER_TYPE swapRollover;
        public double tickSize;
        public double tickValue;
        public int quoteId;
        public String timeString;
        public double leverage;
        public boolean currencyPair;

    public ListSymbolRecord() {
    }

    public ListSymbolRecord(double ask, double bid, String currency, String currencyProfit, String description, int instantMaxVolume, double high, double low, String symbol, long time, int type, String groupName, String categoryName, boolean longOnly, Long starting, Long expiration, int stepRuleId, int stopsLevel, double lotMax, double lotMin, double lotStep, int precision, Long contractSize, Long initialMargin, double marginHedged, boolean marginHedgedStrong, Long marginMaintenance, MARGIN_MODE marginMode, double percentage, PROFIT_MODE profitMode, double spreadRaw, double spreadTable, boolean swapEnable, double swapLong, double swapShort, SWAP_TYPE swapType, SWAP_ROLLOVER_TYPE swapRollover, double tickSize, double tickValue, int quoteId, String timeString, double leverage, boolean currencyPair) {
        this.ask = ask;
        this.bid = bid;
        this.currency = currency;
        this.currencyProfit = currencyProfit;
        this.description = description;
        this.instantMaxVolume = instantMaxVolume;
        this.high = high;
        this.low = low;
        this.symbol = symbol;
        this.time = time;
        this.type = type;
        this.groupName = groupName;
        this.categoryName = categoryName;
        this.longOnly = longOnly;
        this.starting = starting;
        this.expiration = expiration;
        this.stepRuleId = stepRuleId;
        this.stopsLevel = stopsLevel;
        this.lotMax = lotMax;
        this.lotMin = lotMin;
        this.lotStep = lotStep;
        this.precision = precision;
        this.contractSize = contractSize;
        this.initialMargin = initialMargin;
        this.marginHedged = marginHedged;
        this.marginHedgedStrong = marginHedgedStrong;
        this.marginMaintenance = marginMaintenance;
        this.marginMode = marginMode;
        this.percentage = percentage;
        this.profitMode = profitMode;
        this.spreadRaw = spreadRaw;
        this.spreadTable = spreadTable;
        this.swapEnable = swapEnable;
        this.swapLong = swapLong;
        this.swapShort = swapShort;
        this.swapType = swapType;
        this.swapRollover = swapRollover;
        this.tickSize = tickSize;
        this.tickValue = tickValue;
        this.quoteId = quoteId;
        this.timeString = timeString;
        this.leverage = leverage;
        this.currencyPair = currencyPair;
    }

    public List<ListSymbolRecord> convertGenericSymbolRecordIntoFireBaseAccepted(List<pro.xstore.api.message.records.SymbolRecord> records){

        List<ListSymbolRecord> data = new LinkedList<>();
        for (int i = 0; i < records.size(); i++)
        {
            //main object (customSymbol) that is listed in data base
            ListSymbolRecord symbolData = new ListSymbolRecord(
                    records.get(i).getAsk(),
                    records.get(i).getBid(),
                    records.get(i).getCurrency(),
                    records.get(i).getCurrencyProfit(),
                    records.get(i).getDescription(),
                    records.get(i).getInstantMaxVolume(),
                    records.get(i).getHigh(),
                    records.get(i).getLow(),
                    records.get(i).getSymbol(),
                    records.get(i).getTime(),
                    records.get(i).getType(),
                    records.get(i).getGroupName(),
                    records.get(i).getCategoryName(),
                    records.get(i).getLongOnly(),
                    records.get(i).getStarting(),
                    records.get(i).getExpiration(),
                    records.get(i).getStepRuleId(),
                    records.get(i).getStopsLevel(),
                    records.get(i).getLotMax(),
                    records.get(i).getLotMin(),
                    records.get(i).getLotStep(),
                    records.get(i).getPrecision(),
                    records.get(i).getContractSize(),
                    records.get(i).getInitialMargin(),
                    records.get(i).getMarginHedged(),
                    records.get(i).getMarginHedgedStrong(),
                    records.get(i).getMarginMaintenance(),
                    records.get(i).getMarginMode(),
                    records.get(i).getPercentage(),
                    records.get(i).getProfitMode(),
                    records.get(i).getSpreadRaw(),
                    records.get(i).getSpreadTable(),
                    records.get(i).isSwapEnable(),
                    records.get(i).getSwapLong(),
                    records.get(i).getSwapShort(),
                    records.get(i).getSwapType(),
                    records.get(i).getSwap_rollover(),
                    records.get(i).getTickSize(),
                    records.get(i).getTickValue(),
                    records.get(i).getQuoteId(),
                    records.get(i).getTimeString(),
                    records.get(i).getLeverage(),
                    records.get(i).isCurrencyPair()
            );

            data.add(i, symbolData);
        }
        return data;
    }
    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyProfit() {
        return currencyProfit;
    }

    public void setCurrencyProfit(String currencyProfit) {
        this.currencyProfit = currencyProfit;
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

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public int getStepRuleId() {
        return stepRuleId;
    }

    public void setStepRuleId(int stepRuleId) {
        this.stepRuleId = stepRuleId;
    }

    public int getStopsLevel() {
        return stopsLevel;
    }

    public void setStopsLevel(int stopsLevel) {
        this.stopsLevel = stopsLevel;
    }

    public double getLotMax() {
        return lotMax;
    }

    public void setLotMax(double lotMax) {
        this.lotMax = lotMax;
    }

    public double getLotMin() {
        return lotMin;
    }

    public void setLotMin(double lotMin) {
        this.lotMin = lotMin;
    }

    public double getLotStep() {
        return lotStep;
    }

    public void setLotStep(double lotStep) {
        this.lotStep = lotStep;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
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

    public double getMarginHedged() {
        return marginHedged;
    }

    public void setMarginHedged(double marginHedged) {
        this.marginHedged = marginHedged;
    }

    public boolean isMarginHedgedStrong() {
        return marginHedgedStrong;
    }

    public void setMarginHedgedStrong(boolean marginHedgedStrong) {
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public PROFIT_MODE getProfitMode() {
        return profitMode;
    }

    public void setProfitMode(PROFIT_MODE profitMode) {
        this.profitMode = profitMode;
    }

    public double getSpreadRaw() {
        return spreadRaw;
    }

    public void setSpreadRaw(double spreadRaw) {
        this.spreadRaw = spreadRaw;
    }

    public double getSpreadTable() {
        return spreadTable;
    }

    public void setSpreadTable(double spreadTable) {
        this.spreadTable = spreadTable;
    }

    public boolean isSwapEnable() {
        return swapEnable;
    }

    public void setSwapEnable(boolean swapEnable) {
        this.swapEnable = swapEnable;
    }

    public double getSwapLong() {
        return swapLong;
    }

    public void setSwapLong(double swapLong) {
        this.swapLong = swapLong;
    }

    public double getSwapShort() {
        return swapShort;
    }

    public void setSwapShort(double swapShort) {
        this.swapShort = swapShort;
    }

    public SWAP_TYPE getSwapType() {
        return swapType;
    }

    public void setSwapType(SWAP_TYPE swapType) {
        this.swapType = swapType;
    }

    public SWAP_ROLLOVER_TYPE getSwapRollover() {
        return swapRollover;
    }

    public void setSwapRollover(SWAP_ROLLOVER_TYPE swapRollover) {
        this.swapRollover = swapRollover;
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

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public double getLeverage() {
        return leverage;
    }

    public void setLeverage(double leverage) {
        this.leverage = leverage;
    }

    public boolean isCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(boolean currencyPair) {
        this.currencyPair = currencyPair;
    }
}
