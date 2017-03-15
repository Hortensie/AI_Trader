package com.hortensie.ai_trader.dbTester.presenter;

import com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by szczesny on 2017-03-15.
 */

public interface ListContentAdapter {

    void presentSymbolData(Single<List<ListSymbolRecord>> recordList, ListContentAdapterPresenter.MyViewHolder holder);
}
