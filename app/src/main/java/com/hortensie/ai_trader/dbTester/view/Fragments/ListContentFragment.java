package com.hortensie.ai_trader.dbTester.view.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-18.
 * Fragment that displays List of Symbols which user can select and get more information
 * use specific one for calculations
 */

public class ListContentFragment extends Fragment{

    private FireBaseModelInterface modelInterface=new FireBaseModel();
    //Single<List<ListSymbolRecord>> recordList;
    //Single<List<ListSymbolRecord>> filteredRecords;
    ListContentAdapterPresenter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        //recordList = modelInterface.getSymbolRecordListFromFireBase("ListSymbolRecords");
        adapter = new ListContentAdapterPresenter(recyclerView.getContext(),modelInterface.getSymbolListFromFireBase("ListSymbolRecords"));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add decoration line
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        return recyclerView;

    }

}
