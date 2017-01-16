package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.command.ChartRangeCommand;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.ChartRangeInfoRecord;
import pro.xstore.api.message.records.RateInfoRecord;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.TickRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.SymbolResponse;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

import static pro.xstore.api.message.codes.PERIOD_CODE.PERIOD_D1;

/**
 * Created by Piotr on 2016-12-11.
 */

class xApiStreamingLoader extends AsyncTask<Void,Void,STickRecord> {

    @Override
    protected STickRecord doInBackground(Void... voids) {

        STickRecord tickRecord= null;

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
                Log.d("json", "User logged in ");
                Log.d("json", "Status: "+loginResponse.getStatus());
                Log.d("json", "StreamSessionID: "+loginResponse.getStreamSessionId());

                StreamingListener streamingListener = new StreamingListener();
                connector.connectStream(streamingListener);
                connector.subscribeCandle("EURUSD");
                SymbolResponse symbolResponse = APICommandFactory.executeSymbolCommand(connector,"EURUSD");
                streamingListener.receiveTickRecord(tickRecord);

                Log.d("json initial",symbolResponse.getSymbol().getAsk()+"");
                Log.d("json current",tickRecord.getAsk()+"");

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

        return tickRecord;
    }

    static void streamingTickRecordReceived(TickRecord tickRecord)
    {
        if(tickRecord.getLevel()==0)
        {

            Log.d("json current price",""+tickRecord.getAsk());
        }


    }

}
