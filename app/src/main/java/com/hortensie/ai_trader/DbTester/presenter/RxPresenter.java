package com.hortensie.ai_trader.DbTester.presenter;

import android.util.Log;

import com.hortensie.ai_trader.DbTester.model.RxModel;
import com.hortensie.ai_trader.DbTester.model.RxModelInterface;
import com.hortensie.ai_trader.DbTester.view.RxView;
import com.hortensie.ai_trader.DbTester.view.RxViewInterface;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-09.
 * is a layer that provides View with data from Model. Presenter also handles background tasks.
 */

public class RxPresenter implements RxPresenterInterface {


    RxModelInterface modelInterface=new RxModel();
    RxViewInterface viewInterface;

    public RxPresenter(RxViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public void showData()
    {
        modelInterface.getData()
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("RxJava","Thread - inside RxPresenter accept");
                        viewInterface.updateUi(s); // change view
                    }
                });



    }



}

