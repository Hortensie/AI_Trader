package com.hortensie.ai_trader.xAPI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.hortensie.ai_trader.R;

/**
 * Created by Piotr Szczesny on 2017-02-16.
 * Activity that contains selectable GUI inputs in order to get data from xAPI server
 * - option to select start date
 * - option to select end date
 * - option to select symbol from FireBase db
 * - option to select period
 * - option to draw CandleChart once all data is available / selected
 */

public class xApiUiInput extends Activity implements View.OnClickListener {

    //reference to CalendarSelector class
    CalendarSelector calendarSelector = new CalendarSelector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super class onCreate to complete creation of activity like view hierarchy
        super.onCreate(savedInstanceState);
        //set UI layout for this activity (res/layout/)
        setContentView(R.layout.activity_x_api_trading_input);
        //initialize and set onClickListener on all buttons

        //button to pick start date
        Button buttonPickStartDate = (Button)findViewById(R.id.buttonPickStartDate);
        buttonPickStartDate.setOnClickListener(this);
        //button to pick end date
        Button buttonPickEndDate = (Button)findViewById(R.id.buttonPickEndDate);
        buttonPickEndDate.setOnClickListener(this);
        //button to set symbol
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
        //automatically get all symbols so user can select them from RecyclerView
        //should be received only once
        FireBaseHandler fireBaseHandler = new FireBaseHandler();
        fireBaseHandler.getDataFromFireBaseDb("Symbols");
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //call AsyncTask to retrieve financial chart range data based on preselected inputs
            case R.id.buttonHistData:
                //call async task only if xAPI sync (connection) is available
                if(xApiConnectionLogin.getGlobalSyncs()!=null)
                {
                    //Get symbol from recyclerView (Selected row)
                    Intent i = getIntent();
                    String temp_symbol = i.getStringExtra("symbol");
                    //get startTime that was selected by calendar
                    long selectedStartDate = CalendarSelector.getStartTime();
                    //get endTime that was selected by calendar
                    long selectedEndDate = CalendarSelector.getEndTime();
                    //prepare chartRangeInfo object that serve as input to AsyncTask
                    ChartRangeInfo chartRangeInfo = new ChartRangeInfo(temp_symbol,PeriodSelector.getTempValue(),selectedStartDate,selectedEndDate);
                    //call async task
                    new xApiRangeDataLoader(chartRangeInfo,this).execute(xApiConnectionLogin.getGlobalSyncs());
                }
                else
                {
                    //if no connection inform user via toast message
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"Server connection lost. Re-try", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                break;

            case R.id.buttonPickStartDate:
                //pick start date(1)
                calendarSelector.dateCalendarInitialization(1);
                break;
            case R.id.buttonPickEndDate:
                //pick end date(2)
                calendarSelector.dateCalendarInitialization(2);
                break;
            case R.id.buttonSetSymbol:
                //works either FireBaseHandler.getInternalCopy().size()==0
                //if for some reason onResume does not retrieve symbols from Db inform user
                if(SymbolRecyclerViewer.getSymbols().size()==0)
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"No symbols available, Get them first from db", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                else {
                    //if symbols are available call RecyclerViewer class so user can pick desired symbol
                    Intent intent = new Intent(this, SymbolRecyclerViewer.class);
                    startActivity(intent);
                }
                break;
            case R.id.drawCandleChart:
                //call drawChart class to draw CandleChart
                Intent drawChart = new Intent(this,CandleChartDrawer.class);
                startActivity(drawChart);
                break;
            default:
                break;
        }
    }
}
