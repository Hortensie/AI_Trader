package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class xApiUiInput extends Activity implements View.OnClickListener {

    CalendarSelector calendarSelector = new CalendarSelector(this);
    FireBaseDb fireBaseDb = new FireBaseDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super class onCreate to complete creation of activity like view hierarchy
        super.onCreate(savedInstanceState);
        //set UI layout for this activity (res/layout/)
        setContentView(R.layout.activity_x_api_trading_input);
        //initialize and set onClickListener on all buttons
        Button buttonGetSymbolsApi = (Button) findViewById(R.id.buttonGetSymbolsApi);
        buttonGetSymbolsApi.setOnClickListener(this);
        Button buttonPickStartDate = (Button)findViewById(R.id.buttonPickStartDate);
        buttonPickStartDate.setOnClickListener(this);
        Button buttonPickEndDate = (Button)findViewById(R.id.buttonPickEndDate);
        buttonPickEndDate.setOnClickListener(this);
        Button buttonGetSymbols = (Button)findViewById(R.id.buttonGetSymbols);
        buttonGetSymbols.setOnClickListener(this);
        Button buttonSetSymbol = (Button)findViewById(R.id.buttonSetSymbol);
        buttonSetSymbol.setOnClickListener(this);
        Button buttonSetPeriod = (Button)findViewById(R.id.select_period);
        buttonSetPeriod.setOnClickListener(this);
        //button to get historical data from xApi
        Button buttonHistData = (Button)findViewById(R.id.buttonHistData);
        buttonHistData.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonHistData:
                if(xApiConnectionLogin.getGlobalSyncs()!=null)
                {
                    Intent i = getIntent();
                    String temp_symbol = i.getStringExtra("symbol");
                    Log.d("json symbol", temp_symbol);
                    long selectedStartDate = CalendarSelector.getStartTime();
                    Log.d("json start", String.valueOf(selectedStartDate));
                    long selectedEndDate = CalendarSelector.getEndTime();
                    Log.d("json end", String.valueOf(selectedEndDate));

                    new xApiRangeDataLoader(temp_symbol,PeriodSelector.getTempValue(),selectedStartDate,selectedEndDate,this).execute(xApiConnectionLogin.getGlobalSyncs());
                }
                else
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"Server connection lost. Re-try", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                break;
            case R.id.buttonGetSymbolsApi:
                fireBaseDb.getDataFromFireBaseDb("Symbols");
                Toast toastSymbols = Toast.makeText(getApplicationContext(),"Symbols were received from database", Toast.LENGTH_SHORT);
                toastSymbols.show();
                break;
            case R.id.buttonPickStartDate:
                calendarSelector.dateCalendarInitialization(1);
                break;
            case R.id.buttonPickEndDate:
                calendarSelector.dateCalendarInitialization(2);
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
            case R.id.buttonSetSymbol:
                //works either FireBaseDb.getInternalCopy().size()==0
                if(TradingSymbol.getSymbols().size()==0)
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"No symbols available, Get them first from db", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                else {
                    Intent intent = new Intent(this, TradingSymbol.class);
                    startActivity(intent);
                }
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
