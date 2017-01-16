package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class xApiUiInput extends Activity implements View.OnClickListener {

    public TextView startDate;
    public TextView endDate;

    CalendarSelector calendarSelector = new CalendarSelector(this);
    FireBaseDb fireBaseDb = new FireBaseDb();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_api_trading_input);

        Button buttonGetData = (Button) findViewById(R.id.buttonGetData);
        buttonGetData.setOnClickListener(this);
        Button buttonPickStartDate = (Button)findViewById(R.id.buttonPickStartDate);
        buttonPickStartDate.setOnClickListener(this);
        Button buttonPickEndDate = (Button)findViewById(R.id.buttonPickEndDate);
        buttonPickEndDate.setOnClickListener(this);
        Button buttonGetSymbols = (Button)findViewById(R.id.buttonGetSymbols);
        buttonGetSymbols.setOnClickListener(this);
        Button buttonShowSymbols = (Button)findViewById(R.id.buttonShowSymbols);
        buttonShowSymbols.setOnClickListener(this);
        Button buttonSetPeriod = (Button)findViewById(R.id.select_period);
        buttonSetPeriod.setOnClickListener(this);

        startDate = (TextView) findViewById(R.id.textViewDataStartDate);
        endDate = (TextView) findViewById(R.id.textViewDataEndDate);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonGetData:
                //new xApiRangeDataLoader("EURUSD",PERIOD_D1,1451660400000L,1483196400000L).execute(xApiConnectionLogin.getGlobalSyncs());
                //new xApiRangeDataLoader().execute(xApiConnectionLogin.getGlobalSyncs());
                fireBaseDb.getDataFromFireBaseDb("Symbols");
                Toast toastSymbols = Toast.makeText(getApplicationContext(),"Symbols were received from database", Toast.LENGTH_SHORT);
                toastSymbols.show();
                break;
            case R.id.buttonPickStartDate:
                calendarSelector.dateCalendarInitialization(startDate);
                break;
            case R.id.buttonPickEndDate:
                calendarSelector.dateCalendarInitialization(endDate);
                break;
            case R.id.buttonGetSymbols:
                if(xApiConnectionLogin.getGlobalSyncs()==null)
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"Server connection lost, re-login", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                else
                {
                    new xApiSymbolLoader(this).execute(xApiConnectionLogin.getGlobalSyncs());
                }
                break;
            case R.id.buttonShowSymbols:
                Intent intent = new Intent(this,TradingSymbol.class);
                startActivity(intent);
                break;
            case R.id.select_period:
                Intent setPeriod = new Intent(this,PeriodSelector.class);
                startActivity(setPeriod);
                break;
            default:
                break;
        }
    }
}
