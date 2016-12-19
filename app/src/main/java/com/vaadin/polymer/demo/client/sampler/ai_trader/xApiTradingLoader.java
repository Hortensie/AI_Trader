package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.AsyncTask;
import android.util.Log;
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

class xApiTradingLoader extends AsyncTask<Void,Void,List<RateInfoRecord>> {

    private SyncAPIConnector apiConnector;
    private String symbol;
    private PERIOD_CODE period_code;
    private long startTime;
    private long endTime;


    xApiTradingLoader(SyncAPIConnector apiConnector, String symbol, PERIOD_CODE period_code, long startTime, long endTime) {
        this.apiConnector = apiConnector;
        this.symbol = symbol;
        this.period_code = period_code;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    protected List<RateInfoRecord> doInBackground(Void... params) {

        List<RateInfoRecord> eurUsdList=null;
        try {
            Log.d("json", "trying to assign sync API connector");
            //SyncAPIConnector syncAPIConnector=params[0];
            Log.d("json", "sync API connector assigned");

                Log.d("json","char range info record");
                ChartRangeInfoRecord record = new ChartRangeInfoRecord(symbol,period_code,startTime,endTime);
               // ChartRangeInfoRecord record = new ChartRangeInfoRecord("EURUSD",PERIOD_D1,1451660400000L,1483196400000L);
                Log.d("json","chart range created");
                ChartRangeCommand chartRangeCommand = APICommandFactory.createChartRangeCommand(record);
                Log.d("json","chart range command created");
                ChartResponse executeChartRangeCommand = APICommandFactory.executeChartRangeCommand(apiConnector,record);
                Log.d("json EURUSD com req",chartRangeCommand.toString());
                eurUsdList = executeChartRangeCommand.getRateInfos();
        }
         catch (APIReplyParseException|APICommunicationException|APICommandConstructionException|APIErrorResponse e) {
            e.printStackTrace();
        }

        return eurUsdList;
    }


    @Override
    protected void onPostExecute(List<RateInfoRecord>  eurUsdList) {
        // Log.d("json onPostExecute",object.toString());

        //initialize firebase connection (instance, reference)
        //FirebaseDb firebaseDb = new FirebaseDb();
        for (int i=0;i<eurUsdList.size();i++)
        {
            //save data to cloud firebase
            Log.d(""+eurUsdList.get(i).getCtm(),"Close Shift: "+""+eurUsdList.get(i).getClose());

            //firebaseDb.saveData(""+eurUsdList.get(i).getCtm(),"Close Shift: ",""+eurUsdList.get(i).getClose());
            //firebaseDb.saveData(""+eurUsdList.get(i).getCtm(),"Open: ",""+eurUsdList.get(i).getOpen());
            //firebaseDb.saveData(""+eurUsdList.get(i).getCtm(),"Vol in lots: ",""+eurUsdList.get(i).getVol());
            //firebaseDb.saveData(""+eurUsdList.get(i).getCtm(),"High Shift: ",""+eurUsdList.get(i).getHigh());
            //firebaseDb.saveData(""+eurUsdList.get(i).getCtm(),"Low Shift: ",""+eurUsdList.get(i).getLow());
        }
        //    Gson gson = new Gson();

    }
}

