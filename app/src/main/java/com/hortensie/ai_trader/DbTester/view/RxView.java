package com.hortensie.ai_trader.dbTester.view;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.model.TensorFlowModel;
import com.hortensie.ai_trader.dbTester.presenter.RxPresenter;
import com.hortensie.ai_trader.dbTester.presenter.RxPresenterInterface;
import com.hortensie.ai_trader.R;

/**
 * Created by Piotr on 2017-01-30.
 * Layer that displays data and reacts to user actions. This could be an Activity, a Fragment, an android.view.View or a Dialog.
 */

public class RxView extends CustomAppCompatActivity implements RxViewInterface{

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    TextView textView;
    private FireBaseModelInterface model=new FireBaseModel(firebaseDatabase.getReference(),firebaseDatabase);
    TensorFlowModel tensorModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        textView = (TextView) findViewById(R.id.rxJava);

        //create RxPresenter object via Interface with connection to current (this) view
        RxPresenterInterface rxPresenterInterface2 = new RxPresenter(this, model);
        //call showData method from rxPresenter interface
        rxPresenterInterface2.showData();
        tensorModel= new TensorFlowModel(getApplicationContext());
        tensorModel.initTensorFlowModel();
    }

    @Override
    //method that update textView widget on current view
    public void updateUi(String s) {
        textView.setText(s); // Change a View
    }

}