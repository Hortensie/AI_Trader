package com.hortensie.ai_trader;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;

/**
 * Created by szczesny on 2017-02-06.
 */

public class MainAiTraderActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aitrader);

        Button candleDrawer = (Button)findViewById(R.id.candleDrawer);
        Button rxJavaTester = (Button)findViewById(R.id.rxJavaTester);

        candleDrawer.setOnClickListener(this);
        rxJavaTester.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.candleDrawer:
                Intent intentCandleDrawer = new Intent(this,CandleChartDrawer.class);
                startActivity(intentCandleDrawer);
                break;
            case R.id.rxJavaTester:
                Intent intentTrader = new Intent(this,RxJavaTest.class);
                startActivity(intentTrader);
                break;
            default:
                break;
        }
    }
}
