package com.hortensie.ai_trader.dbTester.presenter;

import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.view.RxViewInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-09.
 * Layer that provides View with data from Model. Presenter also handles background tasks.
 */

public class RxPresenter implements RxPresenterInterface {


    private FireBaseModelInterface model;
    private RxViewInterface viewInterface;



    //constructor which initialize View Interface
    public RxPresenter(RxViewInterface viewInterface, FireBaseModelInterface model) {
        this.viewInterface = viewInterface;
        this.model = model;
    }


    //method that subscribe to Observable object and creates new Thread for operations
    //Once received updates UI on main thread
    public void showData()
    {
        model.getData()
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        viewInterface.updateUi(s); // update TextView from activity view
                    }
                });



    }



}

