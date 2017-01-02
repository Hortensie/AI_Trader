package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class xApiLogin extends Activity implements View.OnClickListener {

    private static SyncAPIConnector globalSyncs;
    private EditText inputLogin;
    private EditText inputPassword;

      public xApiLogin() {
    }

    public static SyncAPIConnector getGlobalSyncs() {
        return globalSyncs;
    }

    private static void setGlobalSyncs(SyncAPIConnector globalSyncss) {
        globalSyncs = globalSyncss;
    }

    private void xApiLoginToServer(long login, String password) {

        try {
            globalSyncs = new SyncAPIConnector(ServerData.ServerEnum.DEMO);
            // Create new credentials
            // Insert your credentials
            Credentials credentials = new Credentials(login, password);
            LoginResponse loginResponse = APICommandFactory.executeLoginCommand(globalSyncs, credentials);
            if (loginResponse.getStatus())
            {
                Toast toastLogged = Toast.makeText(getApplicationContext(),"User logged in",Toast.LENGTH_SHORT);
                toastLogged.show();
                Log.d("json", "user logged in, FIRST");
                //if user was successfully logged in call Async Task to retrieve financial data
                //and save it to FireBase database
                setGlobalSyncs(globalSyncs);
                Toast toastSync = Toast.makeText(getApplicationContext(),"Global sync was set",Toast.LENGTH_LONG);
                toastSync.show();
            }
            else
            {
                Log.d("json", "user not logged in, FIRST");
            }

        } catch (IOException|APICommandConstructionException|APICommunicationException|APIReplyParseException|APIErrorResponse e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_api_logi);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Button buttonGetdata = (Button) findViewById(R.id.buttonGetData);
        inputLogin = (EditText) findViewById(R.id.editTextLogin);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(this);
        buttonGetdata.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonLogin:
                //call AsyncTask in order to login and retrieve data from xAPI and then save it @Firebase Database
                long login = Long.parseLong(inputLogin.getText().toString());
                xApiLoginToServer(login, inputPassword.getText().toString());
                break;
            case R.id.buttonGetData:
                Intent intent = new Intent(this,xApiTradingInput.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
