package com.hortensie.ai_trader.xAPI;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.records.RateInfoRecord;

/**
 * Created by Piotr on 2017-01-27.
 * Class that convert xAPI data to CandleEntry required by Chart Drawer
 */

public class ApiCandleConverter {

    //this function can be moved outside this class
    public List<CandleEntry> saveApiRecordsToCandleEntryList (List<RateInfoRecord> records, ChartRangeInfo chartRangeInfo)
    {
        List<CandleEntry> data = new LinkedList<>();
        for (int i = 0; i < records.size(); i++)
        {

            SymbolData symbolData = new SymbolData(
                    records.get(i).getCtm(),
                    records.get(i).getVol(),
                    chartRangeInfo

            );

            CandleEntry candleEntry = new CandleEntry(
                    i,
                    (float) (records.get(i).getHigh()+records.get(i).getOpen()),
                    (float) (records.get(i).getLow()+records.get(i).getOpen()),
                    (float)  records.get(i).getOpen(),
                    (float) (records.get(i).getClose()+records.get(i).getOpen())
                    ,symbolData

            );

            data.add(i,candleEntry);
        }
        return data;
    }

}
