package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.command.ChartRangeCommand;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.ChartRangeInfoRecord;
import pro.xstore.api.message.records.RateInfoRecord;
import pro.xstore.api.message.records.SymbolRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.ServerData.ServerEnum;
import pro.xstore.api.sync.SyncAPIConnector;

import static pro.xstore.api.message.codes.PERIOD_CODE.PERIOD_D1;

/**
 * Created by Piotr on 2016-12-08.
 */

public class xApiTradingLoader extends AsyncTask<Void,Void,List<RateInfoRecord>> {


    @Override
    protected List<RateInfoRecord> doInBackground(Void... voids) {

        List<RateInfoRecord> eurUsdList = new LinkedList();
        try {
            // Create new connector
            Log.d("json", "trying to log in");
            SyncAPIConnector connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);

            // Create new credentials
            // Insert your credentials
            Credentials credentials = new Credentials(10073026, "devil666");

            LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);

            // Check if user logged in correctly
            if (loginResponse.getStatus())
            {
                // Print the message on console
                Log.d("json", "User logged in");
                Log.d("json", "Status: "+loginResponse.getStatus());
                Log.d("json", "StreamSessionID: "+loginResponse.getStreamSessionId());
                //gets all symbols

                /*
                AllSymbolsResponse availableSymbols = APICommandFactory.executeAllSymbolsCommand(connector);

                //print message to console
                Log.d("json", "available symbols");
                for (SymbolRecord symbol : availableSymbols.getSymbolRecords())
                {
                    object.put("Symbol",symbol.getSymbol());
                    object.put("Ask",symbol.getAsk());
                    object.put("Bid",symbol.getBid());
                    object.put("Time",symbol.getTime());
                    Log.d("json do In background","-->"+symbol.getSymbol()+"Ask: "+symbol.getAsk()+"Bid: "+symbol.getBid()+"Czas: "+symbol.getTime());
                }
                */
                Log.d("json","char range info record");
                ChartRangeInfoRecord record = new ChartRangeInfoRecord("EURUSD",PERIOD_D1,1451660400000L,1483196400000L);
                ChartRangeCommand chartRangeCommand = APICommandFactory.createChartRangeCommand(record);
                ChartResponse executeChartRangeCommand = APICommandFactory.executeChartRangeCommand(connector,record);
                Log.d("json EURUSD com req",chartRangeCommand.toString());
                eurUsdList = executeChartRangeCommand.getRateInfos();

            }
            else
            {
                Log.d("json","Error, Coudn't logged in");
            }

            // Close connection
            connector.close();
            Log.d("json","connection closed");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIReplyParseException e) {
            e.printStackTrace();
        } catch (APICommunicationException e) {
            e.printStackTrace();
        } catch (APICommandConstructionException e) {
            e.printStackTrace();
        } catch (APIErrorResponse apiErrorResponse) {
            apiErrorResponse.printStackTrace();
        }

        return eurUsdList;
    }


    @Override
    protected void onPostExecute(List<RateInfoRecord>  eurUsdList) {
        // Log.d("json onPostExecute",object.toString());

        //initialize firebase connection (instance, reference)
        FirebaseDb firebaseDb = new FirebaseDb();
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

