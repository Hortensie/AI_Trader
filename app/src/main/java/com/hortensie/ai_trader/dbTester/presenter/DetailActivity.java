package com.hortensie.ai_trader.dbTester.presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.view.DetailActivityViewInterface;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-26.
 * Class that request detailed information's about symbol once recycler row was clicked
 * Presenter receive data from FireBase and sends back to DetailActivity view via onNext updateTitle method
 */

public class DetailActivity extends AppCompatActivity implements DetailActivityInter {

    private FireBaseModelInterface modelInterface;
    private DetailActivityViewInterface detailViewInterface;

    public DetailActivity(DetailActivityViewInterface detailInterface, FireBaseModelInterface modelInterface) {
        this.detailViewInterface = detailInterface;
        this.modelInterface = modelInterface;
    }

    @Override
    public void showSymbolDetails() {
        Log.d("RxJava Presenter","inside showDetails");
        //RxJava2 observer for Flowable
        modelInterface.getSymbolListFromFireBase("ListSymbolRecords")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ListSymbolRecord>>() {
                    @Override
                    public void accept(List<ListSymbolRecord> listSymbolRecordList) throws Exception {
                        detailViewInterface.updateTitle(listSymbolRecordList);
                    }
                });
        /*
        modelInterface.getListSymbolRecordFromFireBase("ListSymbolRecords")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber <List<ListSymbolRecord>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //request 200 items - same number as recycler viewer row list item
                        s.request(20);
                    }

                    @Override
                    public void onNext(List<ListSymbolRecord> list) {
                        //call update detail view method
                        detailViewInterface.updateTitle(list);
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
                                   detailInterface.updateTitle(dataFromDb);
                                   }
                           }
                );
                */
    }

}
