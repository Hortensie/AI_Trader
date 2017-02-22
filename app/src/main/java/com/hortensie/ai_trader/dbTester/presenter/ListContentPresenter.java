package com.hortensie.ai_trader.dbTester.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.view.ListContentFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-20.
 * Adapter for ListContent Recycler View
 */

public class ListContentPresenter extends RecyclerView.Adapter<ListContentFragment.ViewHolder>{

        //reference to FireBaseModelInterface
        private FireBaseModelInterface modelInterface=new FireBaseModel();
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 5;
        //private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlaceAvators;

    public ListContentPresenter(Context context) {
            Resources resources = context.getResources();
            //mPlaces = resources.getStringArray(R.array.places);

            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();
        }


    @Override
    public ListContentFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListContentFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
        public void onBindViewHolder(final ListContentFragment.ViewHolder holder, final int position) {
        holder.avatar.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);

        //RxJava2 observer
        modelInterface.getDataFromFireBase("Symbols")
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Consumer<List<String>>() {

                    @Override
                    public void accept(List<String> strings) throws Exception {
                        holder.name.setText(strings.get(position));
                    }

                });

            //holder.name.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
}



