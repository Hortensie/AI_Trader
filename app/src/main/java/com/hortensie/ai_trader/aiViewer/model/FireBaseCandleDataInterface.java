package com.hortensie.ai_trader.aiViewer.model;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by szczesny on 2017-03-20.
 * interface for FireBaseCandleData
 */

public interface FireBaseCandleDataInterface {

    Observable<List<CandleEntryRecord>> getCandleListFromFireBase(final String symbol, final String period);
}
