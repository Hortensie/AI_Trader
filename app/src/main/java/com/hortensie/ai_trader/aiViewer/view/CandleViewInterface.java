package com.hortensie.ai_trader.aiViewer.view;

import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;

import java.util.List;

/**
 * Created by szczesny on 2017-03-20.
 */

public interface CandleViewInterface {
    void updateChartOnUi(List<CandleEntry> candleEntries, String label);
}
