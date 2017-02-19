package com.hortensie.ai_trader.dbTester.model;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by szczesny on 2017-02-09.
 * Data access layer such as database API or remote server API.
 */

public class RxModel implements RxModelInterface {



    @Override
    public Observable<String> getData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {

                    e.onNext("Home sweet home"); // Emit the contents of the URL

                    e.onComplete(); // Nothing more to emit
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
