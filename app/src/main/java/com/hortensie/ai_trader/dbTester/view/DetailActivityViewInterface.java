package com.hortensie.ai_trader.dbTester.view;
import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

/**
 * Created by szczesny on 2017-02-26.
 * Interface between DetailActivity -> DetailActivityView
 */

public interface DetailActivityViewInterface {

    void updateTitle(List<ListSymbolRecord> inputList);

}
