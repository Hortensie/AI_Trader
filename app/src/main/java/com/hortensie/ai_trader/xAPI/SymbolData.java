package com.hortensie.ai_trader.xAPI;
/**
 * Created by Piotr on 2017-01-24.
 * SymbolData.class which act as value when saving data for FireBase database
 */

public class SymbolData {

    //public required by FireBase database
    public Long time;
    public double vol;
    public ChartRangeInfo chartRangeInfo;

    // Default constructor required for calls to DataSnapshot.getValue
    public SymbolData() {
    }

    public SymbolData(Long time, double vol, ChartRangeInfo chartRangeInfo) {
        this.time = time;
        this.vol = vol;
        this.chartRangeInfo=chartRangeInfo;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public ChartRangeInfo getChartRangeInfo() {
        return chartRangeInfo;
    }

    public void setChartRangeInfo(ChartRangeInfo chartRangeInfo) {
        this.chartRangeInfo = chartRangeInfo;
    }
}
