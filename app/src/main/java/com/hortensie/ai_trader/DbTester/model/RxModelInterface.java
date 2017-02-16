package com.hortensie.ai_trader.DbTester.model;

import io.reactivex.Observable;

/**
 * Created by szczesny on 2017-02-10.
 */

public interface RxModelInterface {

    Observable<String> getData();
}
