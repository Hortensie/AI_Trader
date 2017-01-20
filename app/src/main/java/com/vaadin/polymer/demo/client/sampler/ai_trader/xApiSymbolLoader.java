package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.SymbolRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 * Created by Piotr Szczesny on 2016-12-08.
 * Class that uses xstore.api to connect to financial server
 * and get all available symbols (list) *
 */

class xApiSymbolLoader extends AsyncTask<SyncAPIConnector,Void,List<SymbolRecord>> {

    private Context context;

    xApiSymbolLoader(Context context) {
        this.context = context;
    }

    @Override
    protected List<SymbolRecord> doInBackground(SyncAPIConnector... params) {

        List<SymbolRecord> symbolList=null;
        try {
                SyncAPIConnector apiConnector = params[0];
                AllSymbolsResponse availableSymbols = APICommandFactory.executeAllSymbolsCommand(apiConnector);
                symbolList = availableSymbols.getSymbolRecords();
                //Close connection
                apiConnector.close();
            }

        catch (APIReplyParseException|APICommunicationException|APICommandConstructionException|APIErrorResponse e) {
            e.printStackTrace();
        }

        return symbolList;

    }

    @Override
    protected void onPostExecute(List<SymbolRecord>  symbolList) {

        if (symbolList==null) {
            Toast toastLogged = Toast.makeText(context,"No connection, please try later", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
        else
        {
            //initialize fire Base connection (instance, reference)
            FireBaseDb firebaseDb = new FireBaseDb();
            for (int i = 0; i < symbolList.size(); i++) {
                firebaseDb.saveDataToFireBaseDb("Symbols", String.valueOf(i), symbolList.get(i).getSymbol());
            }
        }
    }

}

