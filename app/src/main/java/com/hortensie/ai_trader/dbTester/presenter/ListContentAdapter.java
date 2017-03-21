package com.hortensie.ai_trader.dbTester.presenter;

import com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by szczesny on 2017-03-15.
 */

public interface ListContentAdapter {

    //void presentFilteredSymbolData(Single<List<ListSymbolRecord>> recordList, ListContentAdapterPresenter.MyViewHolder holder, String query);
    Observable<List<ListSymbolRecord>> filterSymbolsData(Observable<List<ListSymbolRecord>> recordList, final ListContentAdapterPresenter.MyViewHolder holder, String query);


    //void presentSymbolData(Single<List<ListSymbolRecord>> recordList, ListContentAdapterPresenter.MyViewHolder holder);
    void presentSymbolsData(Observable<List<ListSymbolRecord>> recordList, ListContentAdapterPresenter.MyViewHolder holder);

}
