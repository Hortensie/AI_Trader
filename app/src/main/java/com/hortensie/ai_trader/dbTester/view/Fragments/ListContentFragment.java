package com.hortensie.ai_trader.dbTester.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapter;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.dbTester.view.DetailActivityView;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Maybe;
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

public class ListContentFragment extends Fragment implements ListContentFragmentInterface{

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
        adapter = new ListContentAdapterPresenter(recyclerView.getContext(),modelInterface.getSymbolRecordListFromFireBase("ListSymbolRecords"));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add decoration line
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        return recyclerView;

    }


    @Override
    public void presentFilteredSymbolData(Single<List<ListSymbolRecord>> recordList, final ListContentAdapterPresenter.MyViewHolder holder, String query) {

        final String finalQuery=query.toLowerCase();

        recordList
                .filter(new Predicate<List<ListSymbolRecord>>() {
                    @Override
                    public boolean test(List<ListSymbolRecord> listSymbolRecords) throws Exception {
                        return listSymbolRecords.get(holder.getAdapterPosition()).getGroupName().equals(finalQuery);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ListSymbolRecord>>() {
                    @Override
                    public void accept(List<ListSymbolRecord> listSymbolRecordList) throws Exception {
                        holder.symbolName.setText(listSymbolRecordList.get(holder.getAdapterPosition()).getDescription());
                        holder.symbolDescription.setText(listSymbolRecordList.get(holder.getAdapterPosition()).getCategoryName());

                    }
                });


    }


    //search method inside recycler viewer
    public void search(SearchView searchView) {
            Log.d("RxJava","Inside seach function");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    //https://www.numetriclabz.com/android-adding-search-functionality-to-recyclerview/
                    //Log.d("RxJava","Inside seach onQueryTextChange");
                    //final String finalQuery = query;

                    //presentFilteredSymbolData(modelInterface.getSymbolRecordListFromFireBase("ListSymbolRecords"),;

                    //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    //filteredRecords = recordList;
                    //adapter=new ListContentAdapterPresenter(recyclerView.getContext(),filteredRecords,filteredRecords);
                    //recyclerView.setAdapter(adapter);

                    //adapter.notifyDataSetChanged();
                    return true;
                }
            });
        }


}
