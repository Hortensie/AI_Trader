package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.codes.PERIOD_CODE;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.ChartRangeInfoRecord;
import pro.xstore.api.message.records.RateInfoRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 * Created by Piotr Szczesny on 2016-12-08.
 * Async Task for retrieving historical data from xAPI server
 * It retrieves data within specific time frame (from startTime to endTime)
 * and for selected symbol & period
 * It gets data and save to FireBase database using saveListToFireBaseDataBase method
 * If successful toast message with be shown
 * If there is no data connection with xAPI server will be terminated
 */

 class xApiRangeDataLoader extends AsyncTask<SyncAPIConnector,Void,Void> {

        static List<CandleEntry> dataSet = new LinkedList<>();
        private String symbol;
        private PERIOD_CODE period_code;
        private long startTime;
        private long endTime;
        private Context context;


    xApiRangeDataLoader(String symbol, PERIOD_CODE period_code, long startTime, long endTime, Context context) {
        this.symbol = symbol;
        this.period_code = period_code;
        this.startTime = startTime;
        this.endTime = endTime;
        this.context = context;
    }

    public static List<CandleEntry> getDataSet() {
        return dataSet;
    }

    public static void setDataSet(List<CandleEntry> dataSet) {
        xApiRangeDataLoader.dataSet = dataSet;
    }

    @Override
    protected Void doInBackground(SyncAPIConnector... params) {
        try
        {
                SyncAPIConnector apiConnector=params[0];
                ChartRangeInfoRecord record = new ChartRangeInfoRecord(symbol,period_code,startTime,endTime);
                ChartResponse executeChartRangeCommand = APICommandFactory.executeChartRangeCommand(apiConnector,record);
                List<RateInfoRecord> eurUsdList = executeChartRangeCommand.getRateInfos();
                if(eurUsdList!=null&&eurUsdList.size()!=0) {
                    FireBaseHandler fireBaseHandler = new FireBaseHandler();
                    fireBaseHandler.saveCandleListToFireBase(fireBaseHandler.saveApiRecordsToCandleEntryList(eurUsdList),symbol,String.valueOf(period_code));
                    dataSet=fireBaseHandler.saveApiRecordsToCandleEntryList(eurUsdList);
                    setDataSet(dataSet);
                }
                else
                {
                    // Close connection
                    apiConnector.close();
                }
        }
         catch (APIReplyParseException|APICommunicationException|APICommandConstructionException|APIErrorResponse e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    protected void onPostExecute(Void avoid)
    {
        {

            Toast toastLogged = Toast.makeText(context,"Data for "+symbol+" successfully saved to database!", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
    }

}

