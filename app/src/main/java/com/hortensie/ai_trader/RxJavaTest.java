package com.hortensie.ai_trader;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.Callable;

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
    TextView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        view= (TextView) findViewById(R.id.rxJava);
        view2= (TextView) findViewById(R.id.rxJava2);


        fetchData
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(s -> {
                    view.setText(view.getText() + "\n" + s); // Change a View
                });

        fetchData2.subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(s -> {view2.setText(view2.getText()+"\n"+s);});
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

    private String fetchData2(String s) {
        return s;
    }

    Observable<String> fetchData2 = Observable.create(e -> {
                                                      e.onNext(fetchData2("1st onNext"));
                                                      e.onNext(fetchData2("2nd onNext"));

    });


}
