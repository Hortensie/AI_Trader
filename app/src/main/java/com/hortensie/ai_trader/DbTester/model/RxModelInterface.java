package com.hortensie.ai_trader.dbTester.model;

import io.reactivex.Observable;

/**
 * Created by szczesny on 2017-02-10.
 * Interface between Presenter --> Model
 */

public interface RxModelInterface {

    //method that getData from some source and return Observable object which can be used by Presenter
    Observable<String> getData();
}
