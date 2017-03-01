package com.hortensie.ai_trader.dbTester.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-20.
 * Adapter for ListContent Fragment that extends Recycler View
 * Adapter gets images from internal Array
 * Adapter gets symbol information's via Subscriber (from FireBase db) (symbol description, category name)
 */

public class ListContentPresenter extends RecyclerView.Adapter<ListContentFragment.ViewHolder>{

    //reference to FireBaseModelInterface
    private FireBaseModelInterface modelInterface=new FireBaseModel();
    // Set numbers of items inside RecyclerView.
    private static final int LENGTH = 200;
    private final Drawable[] mPlaceAvatars;

    public ListContentPresenter(Context context)
    {
            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
        mPlaceAvatars = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvatars.length; i++) {
                mPlaceAvatars[i] = a.getDrawable(i);
            }
            a.recycle();
    }

    @Override
    public ListContentFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListContentFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
        public void onBindViewHolder(final ListContentFragment.ViewHolder holder, int position) {
        //set image inside recycler row
        holder.avatar.setImageDrawable(mPlaceAvatars[position % mPlaceAvatars.length]);

        //get symbol name and symbol description from Fire base db
        //RxJava2 observer
        modelInterface.getDataFromFireBase("FinalSymbols")
                //take LENGTH number of items from Flowable Publisher
                .take(LENGTH)
                //start subscription on new Thread
                .subscribeOn(Schedulers.newThread())
                //update results on main UI thread
                .observeOn(AndroidSchedulers.mainThread(),false,LENGTH)
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataSnapshot>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //request LENGTH number of items
                        s.request(LENGTH);
                    }

                    @Override
                    public void onNext(DataSnapshot dataSnapshot) {
                        //update symbol and symbol description inside recycler viewer row
                        holder.symbolName.setText(dataSnapshot.child(String.valueOf(holder.getAdapterPosition())).child("description").getValue().toString());
                        holder.symbolDescription.setText(dataSnapshot.child(String.valueOf(holder.getAdapterPosition())).child("categoryName").getValue().toString());
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



