package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pro.xstore.api.message.codes.PERIOD_CODE;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.command.ChartRangeCommand;
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
 * comment
 */

 class xApiRangeDataLoader extends AsyncTask<SyncAPIConnector,Void,List<RateInfoRecord>> {


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

    @Override
    protected List<RateInfoRecord> doInBackground(SyncAPIConnector... params) {

        List<RateInfoRecord> eurUsdList=null;
        try {
                SyncAPIConnector apiConnector=params[0];
                ChartRangeInfoRecord record = new ChartRangeInfoRecord(symbol,period_code,startTime,endTime);
                ChartRangeCommand chartRangeCommand = APICommandFactory.createChartRangeCommand(record);
                ChartResponse executeChartRangeCommand = APICommandFactory.executeChartRangeCommand(apiConnector,record);
                Log.d("json EURUSD com req",chartRangeCommand.toString());
                eurUsdList = executeChartRangeCommand.getRateInfos();

            // Close connection
            apiConnector.close();
            Log.d("json","connection closed");
        }
         catch (APIReplyParseException|APICommunicationException|APICommandConstructionException|APIErrorResponse e) {
            e.printStackTrace();
        }

        return eurUsdList;

    }


    @Override
    protected void onPostExecute(List<RateInfoRecord>  eurUsdList) {
        Log.d("json","onPostExecute");
        if(eurUsdList!=null&&eurUsdList.size()!=0) {

            //initialize fireBase connection (instance, reference)
            FireBaseDb firebaseDb = new FireBaseDb();
            for (int i = 0; i < eurUsdList.size(); i++) {
                //save data to cloud FireBase

                firebaseDb.writeNewSymbol(FireBaseDb.EncodeString(symbol),String.valueOf(eurUsdList.get(i).getCtm()),
                                                eurUsdList.get(i).getCtm(),
                                                eurUsdList.get(i).getOpen(),
                                                eurUsdList.get(i).getHigh(),
                                                eurUsdList.get(i).getLow(),
                                                eurUsdList.get(i).getClose(),
                                                eurUsdList.get(i).getVol()
                                        );

            }
        }
        else
        {
            Toast toastLogged = Toast.makeText(context,"No data for selected inputs", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
    }
}

