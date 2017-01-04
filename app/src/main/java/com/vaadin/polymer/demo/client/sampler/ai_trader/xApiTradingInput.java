package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class xApiTradingInput extends Activity implements View.OnClickListener {

    public TextView startDate;
    private TextView endDate;
    private final DateCalendar objectDateCalendar = new DateCalendar(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_api_trading_input);

        Button buttonStream = (Button) findViewById(R.id.buttonStream);
        buttonStream.setOnClickListener(this);
        Button buttonPickStartDate = (Button)findViewById(R.id.buttonPickStartDate);
        buttonPickStartDate.setOnClickListener(this);
        Button buttonPickEndDate = (Button)findViewById(R.id.buttonPickEndDate);
        buttonPickEndDate.setOnClickListener(this);
        startDate = (TextView) findViewById(R.id.textViewDataStartDate);
        endDate = (TextView) findViewById(R.id.textViewDataEndDate);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonStream:
                //new xApiTradingLoader("EURUSD",PERIOD_D1,1451660400000L,1483196400000L).execute(xApiLogin.getGlobalSyncs());
                new xApiTradingLoader().execute(xApiLogin.getGlobalSyncs());
                break;
            case R.id.buttonPickStartDate:
                objectDateCalendar.initiateDatePicker();
                break;
            case R.id.buttonPickEndDate:

                break;
            default:
                break;
        }
    }


}
