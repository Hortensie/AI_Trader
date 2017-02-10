package com.hortensie.ai_trader.DbTester.view;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hortensie.ai_trader.R;

/**
 * Created by Piotr on 2017-01-30.
 * s a layer that displays data and reacts to user actions. On Android, this could be an Activity, a Fragment, an android.view.View or a Dialog.
 */

public class RxView extends Activity {

    TextView view;
    TextView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        view = (TextView) findViewById(R.id.rxJava);
        view2 = (TextView) findViewById(R.id.rxJava2);
    }
}
