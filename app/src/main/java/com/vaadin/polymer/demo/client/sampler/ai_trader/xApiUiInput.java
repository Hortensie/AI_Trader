package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class xApiUiInput extends Activity implements View.OnClickListener {

    CalendarSelector calendarSelector = new CalendarSelector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super class onCreate to complete creation of activity like view hierarchy
        super.onCreate(savedInstanceState);
        //set UI layout for this activity (res/layout/)
        setContentView(R.layout.activity_x_api_trading_input);
        //initialize and set onClickListener on all buttons

        Button buttonPickStartDate = (Button)findViewById(R.id.buttonPickStartDate);
        buttonPickStartDate.setOnClickListener(this);
        Button buttonPickEndDate = (Button)findViewById(R.id.buttonPickEndDate);
        buttonPickEndDate.setOnClickListener(this);
        Button buttonSetSymbol = (Button)findViewById(R.id.buttonSetSymbol);
        buttonSetSymbol.setOnClickListener(this);
        //button to get historical data from xApi
        Button buttonHistData = (Button)findViewById(R.id.buttonHistData);
        buttonHistData.setOnClickListener(this);
        //button to start CandleChartDrawer
        Button buttonCandleChart = (Button)findViewById(R.id.drawCandleChart);
        buttonCandleChart.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        //should be received only once

        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonHistData:
                if(xApiConnectionLogin.getGlobalSyncs()!=null)
                {
                    Intent i = getIntent();
                    String temp_symbol = i.getStringExtra("symbol");
                    long selectedStartDate = CalendarSelector.getStartTime();
                    long selectedEndDate = CalendarSelector.getEndTime();
                    ChartRangeInfo chartRangeInfo = new ChartRangeInfo(temp_symbol,PeriodSelector.getTempValue(),selectedStartDate,selectedEndDate);
                    new xApiRangeDataLoader(chartRangeInfo,this).execute(xApiConnectionLogin.getGlobalSyncs());
                }
                else
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"Server connection lost. Re-try", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                break;

            case R.id.buttonPickStartDate:
                calendarSelector.dateCalendarInitialization(1);
                break;
            case R.id.buttonPickEndDate:
                calendarSelector.dateCalendarInitialization(2);
                break;
            case R.id.buttonSetSymbol:
                //works either FireBaseHandler.getInternalCopy().size()==0
                if(SymbolRecyclerViewer.getSymbols().size()==0)
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"No symbols available, Get them first from db", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                else {
                    Intent intent = new Intent(this, SymbolRecyclerViewer.class);
                    startActivity(intent);
                }
                break;
            case R.id.drawCandleChart:
                Intent drawChart = new Intent(this,CandleChartDrawer.class);
                startActivity(drawChart);
                break;
            default:
                break;
        }
    }
}
