package com.hortensie.ai_trader.dbTester.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.dbTester.view.DetailActivityView;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;

/**
 * Created by szczesny on 2017-02-18.
 * Fragment that displays List of Symbols which user can select and get more information
 * use specific one for calculations
 */

public class ListContentFragment extends Fragment implements ListContentFragmentInterface{

    private FireBaseModelInterface modelInterface=new FireBaseModel();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ListContentAdapterPresenter adapter = new ListContentAdapterPresenter(recyclerView.getContext(), modelInterface.getSymbolRecordListFromFireBase("ListSymbolRecords"));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add decoration line
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        return recyclerView;

    }

    //search method inside recycler viewer
    public void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //https://www.numetriclabz.com/android-adding-search-functionality-to-recyclerview/

                final String finalQuery = query;
                /*
                final Flowable<List<ListSymbolRecord>> filteredRecords =
                        recordList
                                .filter(new Predicate<List<ListSymbolRecord>>() {
                                    @Override
                                    public boolean test(List<ListSymbolRecord> listSymbolRecordList) throws Exception {
                                        return listSymbolRecordList.get(1).getGroupName().equals(finalQuery);
                                    }
                                });

                return f;
            }
        });
        */
                return true;
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        //needs to be public so ListContentAdapterPresenter can reference to it
        public TextView symbolName;
        public TextView symbolDescription;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avatar = (ImageView) itemView.findViewById(R.id.list_avatar);
            //symbol name inside recycler
            symbolName = (TextView) itemView.findViewById(R.id.list_title);
            //symbol description inside recycler
            symbolDescription = (TextView) itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivityView.class);
                    //put position inside intent so Detail Activity can reference to it
                    intent.putExtra(DetailActivityView.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }



}
