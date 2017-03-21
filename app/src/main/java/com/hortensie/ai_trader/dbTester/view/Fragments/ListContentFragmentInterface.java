package com.hortensie.ai_trader.dbTester.view.Fragments;

import android.support.v7.widget.SearchView;

import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by szczesny on 2017-03-12.
 */

public interface ListContentFragmentInterface {
    void search(SearchView searchView, ListContentAdapterPresenter.MyViewHolder holder);
    //void presentFilteredSymbolData(Single<List<ListSymbolRecord>> recordList, ListContentAdapterPresenter.MyViewHolder holder, String query);
}
