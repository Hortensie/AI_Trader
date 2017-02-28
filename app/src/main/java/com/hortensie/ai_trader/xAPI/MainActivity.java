package com.hortensie.ai_trader.xAPI;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hortensie.ai_trader.dbTester.view.MainDbTester;
import com.hortensie.ai_trader.R;

/** Created by Piotr on 2017-01-03
 * Main activity (user see it at first)
*/

public class MainActivity extends Activity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    /*
    static {
        System.loadLibrary("native-lib");
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // enable transitions animation
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(R.string.textview_testtext);
        Button buttonXApi = (Button) findViewById(R.id.buttonXApi);
        buttonXApi.setOnClickListener(this);
        //button for AI Trader
        Button buttonTrader = (Button) findViewById(R.id.historicalData);
        buttonTrader.setOnClickListener(this);
        //?
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.

    public native String stringFromJNI();
     */

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //option to login to xAPI server in order to get financial data
            case R.id.buttonXApi:
                //getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(this,xApiConnectionLogin.class);
                //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                startActivity(intent);
                break;
            //option to draw Candle Chart & test RxJava 2
            case R.id.historicalData:
                Intent intentTrader = new Intent(this,MainDbTester.class);
                startActivity(intentTrader);
                break;
            default:
                break;
        }
    }

}
