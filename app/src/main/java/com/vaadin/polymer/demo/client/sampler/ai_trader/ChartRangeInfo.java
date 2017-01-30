package com.vaadin.polymer.demo.client.sampler.ai_trader;

import pro.xstore.api.message.codes.PERIOD_CODE;
import pro.xstore.api.message.records.ChartRangeInfoRecord;

/**
 * Created by Piotr on 2017-01-25.
 * ChartRangeInfo.class act as input object for retrieving financial data from xAPI server
 * Input object contain data selected from end user (symbol name, period, start time, end time)
 */

public class ChartRangeInfo extends ChartRangeInfoRecord{

    public String symbol;
    public PERIOD_CODE period;
    public long start;
    public long end;

    public ChartRangeInfo(String symbol, PERIOD_CODE period, long start, long end) {
        super(symbol, period, start, end);
        this.symbol=symbol;
        this.period=period;
        this.start=start;
        this.end=end;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public PERIOD_CODE getPeriod() {
        return period;
    }

    public void setPeriod(PERIOD_CODE period) {
        this.period = period;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
