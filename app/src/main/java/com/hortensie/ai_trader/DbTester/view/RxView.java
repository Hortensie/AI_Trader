package com.hortensie.ai_trader.DbTester.view;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hortensie.ai_trader.DbTester.presenter.RxPresenter;
import com.hortensie.ai_trader.DbTester.presenter.RxPresenterInterface;
import com.hortensie.ai_trader.R;

/**
 * Created by Piotr on 2017-01-30.
 * Layer that displays data and reacts to user actions. This could be an Activity, a Fragment, an android.view.View or a Dialog.
 */

public class RxView extends Activity implements RxViewInterface{

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        textView = (TextView) findViewById(R.id.rxJava);
        //create RxPresenter object via Interface with connection to current (this) view
        RxPresenterInterface rxPresenterInterface2 = new RxPresenter(this);
        //call showData method from rxPresenter interface
        rxPresenterInterface2.showData();
    }

    @Override
    //method that update textView widget on current view
    public void updateUi(String s) {
        textView.setText(s); // Change a View
    }

}
