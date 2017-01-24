package com.vaadin.polymer.demo.client.sampler.ai_trader;

/**
 * Created by Piotr on 2017-01-24.
 */

public class SymbolData {

    public Long time;
    public double vol;

    // Default constructor required for calls to DataSnapshot.getValue
    public SymbolData() {
    }

    public SymbolData(Long time, double vol) {
        this.time = time;
        this.vol = vol;
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
}
