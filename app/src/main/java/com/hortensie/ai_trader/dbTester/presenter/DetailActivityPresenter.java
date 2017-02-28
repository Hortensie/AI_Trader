package com.hortensie.ai_trader.dbTester.presenter;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.view.DetailActivityViewInterface;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szczesny on 2017-02-26.
 * Class that request detailed information's about symbol once recycler row was clicked
 * Presenter receive data from FireBase and sends back to DetailActivity view via onNext updateTitle method
 */

public class DetailActivityPresenter extends AppCompatActivity implements DetailActivityPresenterInterface {

    private FireBaseModelInterface modelInterface=new FireBaseModel();
    private DetailActivityViewInterface detailViewInterface;

    public DetailActivityPresenter(DetailActivityViewInterface detailInterface) {
        this.detailViewInterface = detailInterface;
    }

    @Override
    public void showDetails() {
        Log.d("RxJava Presenter","inside showDetails");
        //RxJava2 observer
        modelInterface.getDataFromFireBase("FinalSymbols")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataSnapshot>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //request 200 items - same number as recycler viewer row list item
                        s.request(200);
                    }

                    @Override
                    public void onNext(DataSnapshot dataSnapshot) {
                        //call update detail view method
                        detailViewInterface.updateTitle(dataSnapshot);
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
