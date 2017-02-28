package com.hortensie.ai_trader.dbTester.view.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.presenter.ListContentPresenter;
import com.hortensie.ai_trader.dbTester.view.DetailActivityView;

/**
 * Created by szczesny on 2017-02-18.
 * Fragment that displays List of Symbols which user can select and get more information
 * use specific one for calculations
 */

public class ListContentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ListContentPresenter adapter = new ListContentPresenter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add decoration line
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        return recyclerView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        //needs to be public so ListContentPresenter can reference to it
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
