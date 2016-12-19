package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 * Created by Piotr on 2016-12-18.
 * comment
 */

class xApiLogin {

    private SyncAPIConnector globalSyncs;
    private Context context;

    xApiLogin(Context context) {
        this.context = context;
    }


    SyncAPIConnector getGlobalSyncs() {
        return globalSyncs;
    }

    private void setGlobalSyncs(SyncAPIConnector globalSyncs) {
        this.globalSyncs = globalSyncs;
    }

    void xApiLoginToServer(long login, String password) {

        try {
            globalSyncs = new SyncAPIConnector(ServerData.ServerEnum.DEMO);
            // Create new credentials
            // Insert your credentials

            Credentials credentials = new Credentials(login, password);
            LoginResponse loginResponse = APICommandFactory.executeLoginCommand(globalSyncs, credentials);
            if (loginResponse.getStatus())
            {
                Toast toastLogged = Toast.makeText(context,"User logged in",Toast.LENGTH_LONG);
                toastLogged.show();
                Log.d("json", "user logged in, FIRST");
                //if user was successfully logged in call Async Task to retrieve financial data
                //and save it to FireBase database
                setGlobalSyncs(globalSyncs);
                Toast toastSync = Toast.makeText(context,"Global sync was set",Toast.LENGTH_LONG);
                toastSync.show();
            }
            else
            {
                Toast toast = Toast.makeText(context,"Error, user coudn't logged in",Toast.LENGTH_LONG);
                toast.show();
            }

        } catch (IOException|APICommandConstructionException|APICommunicationException|APIReplyParseException|APIErrorResponse e) {
            e.printStackTrace();
        }

        //PingCommand pingCommand = APICommandFactory.createPingCommand();
      //  pingCommand.getCommandName();
       // PingResponse pingResponse = APICommandFactory.executePingCommand(globalSyncs);
      //  Log.d("json", pingResponse.toString());

    }
}
