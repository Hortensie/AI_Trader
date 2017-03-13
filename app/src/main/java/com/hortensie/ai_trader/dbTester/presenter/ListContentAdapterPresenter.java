package com.hortensie.ai_trader.dbTester.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment.*;

/**
 * Created by szczesny on 2017-02-20.
 * Adapter for ListContent Fragment that extends Recycler View
 * Adapter gets images from internal Array
 * Adapter gets symbol information's via Subscriber (from FireBase db) (symbol description, category name)
 */

public class ListContentAdapterPresenter extends RecyclerView.Adapter<com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment.ViewHolder>{

    //reference to FireBaseModelInterface
    //private FireBaseModelInterface modelInterface=new FireBaseModel();
    // Set numbers of items inside RecyclerView.
    private static final int LENGTH = 20;
    private final Drawable[] mPlaceAvatars;
    //private Flowable<List<ListSymbolRecord>> recordList;
    private Single<List<ListSymbolRecord>> recordList;

    public ListContentAdapterPresenter(Context context, Single<List<ListSymbolRecord>>  recordList)
    {
            this.recordList=recordList;
            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvatars = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvatars.length; i++) {
                mPlaceAvatars[i] = a.getDrawable(i);
            }
            a.recycle();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
        //set image inside recycler row
        holder.avatar.setImageDrawable(mPlaceAvatars[holder.getAdapterPosition() % mPlaceAvatars.length]);

        //get symbol name and symbol description from Fire base db
        //RxJava2 observer
        recordList
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ListSymbolRecord>>() {
                    @Override
                    public void accept(List<ListSymbolRecord> listSymbolRecordList) throws Exception {
                      holder.symbolName.setText(listSymbolRecordList.get(holder.getAdapterPosition()).getDescription());
                      holder.symbolDescription.setText(listSymbolRecordList.get(holder.getAdapterPosition()).getCategoryName());

                    }
                });

        /*
        recordList
                //take LENGTH number of items from Flowable Publisher
                .take(LENGTH)

                .filter(new Predicate<List<ListSymbolRecord>>() {
                    @Override
                    public boolean test(List<ListSymbolRecord> listSymbolRecords) throws Exception {
                        return listSymbolRecords.get(holder.getAdapterPosition()).getGroupName().equals("UK");
                    }
                })

                //start subscription on new Thread
                //.subscribeOn(Schedulers.newThread())
                //update results on main UI thread
                .observeOn(AndroidSchedulers.mainThread(),false,LENGTH)
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber <List<ListSymbolRecord>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //request LENGTH number of items
                        s.request(LENGTH);
                    }

                    @Override
                    public void onNext(List<ListSymbolRecord> list) {
                       //update symbol and symbol description inside recycler viewer row
                       Log.d("RxJava","Inside holder");
                       holder.symbolName.setText(list.get(holder.getAdapterPosition()).getDescription());
                       Log.d("RxJava",list.get(holder.getAdapterPosition()).getDescription());
                       holder.symbolDescription.setText(list.get(holder.getAdapterPosition()).getCategoryName());
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
                /*
                .subscribe(new Consumer<DataSnapshot>() {
                               @Override
                               public void accept(DataSnapshot dataFromDb) throws Exception {
                                  //Log.d("RxJava Presenter",dataFromDb.toString());
                                  holder.name.setText(dataFromDb.child(String.valueOf(holder.getAdapterPosition())).child("description").getValue().toString());
                                  holder.description.setText(dataFromDb.child(String.valueOf(holder.getAdapterPosition())).child("categoryName").getValue().toString());
                               }
                           }
                );
                */

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }



}




