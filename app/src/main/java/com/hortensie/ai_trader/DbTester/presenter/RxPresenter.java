package com.hortensie.ai_trader.DbTester.presenter;

/**
 * Created by szczesny on 2017-02-09.
 * is a layer that provides View with data from Model. Presenter also handles background tasks.
 */

public class RxPresenter {

    /*
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


    Observable<String> fetchData2 = Observable.create(e -> {
                                                      e.onNext(fetchData2("1st onNext"));
                                                      e.onNext(fetchData2("2nd onNext"));

    });
    */
}
