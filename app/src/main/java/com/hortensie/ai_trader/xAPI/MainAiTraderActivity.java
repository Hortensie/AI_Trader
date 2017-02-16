package com.hortensie.ai_trader.xAPI;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hortensie.ai_trader.DbTester.view.RxView;
import com.hortensie.ai_trader.R;

/**
 * Created by Piotr Szczesny on 2017-02-16.
 * Class where user can draw CandleChart based on local data
 * Class where RxJava 2 & MVP design pattern is being used
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
                Intent intentTrader = new Intent(this,RxView.class);
                startActivity(intentTrader);
                break;
            default:
                break;
        }
    }
}
