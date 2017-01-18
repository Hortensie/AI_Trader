package com.vaadin.polymer.demo.client.sampler.ai_trader;

/**
 * Created by Piotr on 2017-01-18.
 */

public class Symbol {

    public long ctm;
    public double open;
    public double high;
    public double low;
    public double close;
    public double vol;

    // Default constructor required for calls to DataSnapshot.getValue
    public Symbol() {
    }

    public Symbol(long ctm, double open, double high, double low, double close, double vol) {
        this.ctm = ctm;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol;
    }

    public void setCtm(long ctm) {
        this.ctm = ctm;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
