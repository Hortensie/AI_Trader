package com.hortensie.ai_trader.aiViewer.presenter;

import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;
import com.google.firebase.database.DataSnapshot;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleDataInterface;
import com.hortensie.ai_trader.aiViewer.view.CandleViewInterface;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-03-20.
 * This Class present FireBaseCandleData
 */

public class CandleDrawer implements CandleDrawerInterface{

    FireBaseCandleDataInterface model;
    CandleViewInterface view;

    public CandleDrawer(FireBaseCandleDataInterface model, CandleViewInterface view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void showChartData(final String symbol, final String period) {
        model.getCandleListFromFireBase(symbol,period)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CandleEntryRecord>>() {
                    @Override
                    public void accept(List<CandleEntryRecord> candleEntries) throws Exception {
                        view.updateChartOnUi(convertListCandleEntryRecordToCandleEntry(candleEntries),"label");
                    }
                });
    }

    public List<CandleEntry> convertListCandleEntryRecordToCandleEntry(List<CandleEntryRecord> entry){

        List<CandleEntry> records = new LinkedList<>();
        for (int i=0;i<entry.size();i++)
        {
            CandleEntry singleRecord = new CandleEntry(
                    entry.get(i).getX(),
                    entry.get(i).getHigh(),
                    entry.get(i).getLow(),
                    entry.get(i).getOpen(),
                    entry.get(i).getClose());
            records.add(singleRecord);
        }
        return records;
    }

}
