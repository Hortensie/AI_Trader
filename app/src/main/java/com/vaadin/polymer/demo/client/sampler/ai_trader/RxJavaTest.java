package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Piotr on 2017-01-30.
 */

public class RxJavaTest extends Activity {


    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        view= (TextView) findViewById(R.id.rxJava);


        fetchData
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        view.setText(view.getText() + "\n" + s); // Change a View
                    }
                });
    }

    Observable<String> fetchData = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            try {
                String data = fetchData("cos");
                e.onNext(data); // Emit the contents of the URL
                e.onComplete(); // Nothing more to emit
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    });

    private String fetchData(String s) {
        return "RxJava Thread";
    }

}
