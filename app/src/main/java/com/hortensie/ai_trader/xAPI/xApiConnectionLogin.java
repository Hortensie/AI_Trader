package com.hortensie.ai_trader.xAPI;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hortensie.ai_trader.R;

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
 * Class that uses xStore.api to connect to Demo financial server
 */

public class xApiConnectionLogin extends Activity implements View.OnClickListener {

    private static SyncAPIConnector globalSyncs;
    private EditText inputLogin;
    private EditText inputPassword;

      public xApiConnectionLogin() {
    }

    public static SyncAPIConnector getGlobalSyncs() {
        return globalSyncs;
    }

    public static void setGlobalSyncs(SyncAPIConnector globalSyncss) {
        globalSyncs = globalSyncss;
    }

    public Credentials setLogin (Long login, String password)
    {
        return new Credentials(login,password);

    }

    public LoginResponse xApiLoginToServer(Credentials credentials, SyncAPIConnector syncAPIConnector) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException, IOException {
        LoginResponse loginResponse = APICommandFactory.executeLoginCommand(syncAPIConnector, credentials);
        setGlobalSyncs(syncAPIConnector);
        return loginResponse;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_api_logi);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        inputLogin = (EditText) findViewById(R.id.editTextLogin);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonLogin:
                //call AsyncTask in order to login and retrieve data from xAPI and then save it @Firebase Database
                long login = Long.parseLong(inputLogin.getText().toString());
                try {
                    LoginResponse loginResponse = xApiLoginToServer(setLogin(login, inputPassword.getText().toString()), new SyncAPIConnector(ServerData.ServerEnum.DEMO));
                    if (loginResponse.getStatus()) {
                        Toast toastLogged = Toast.makeText(getApplicationContext(), "User logged in", Toast.LENGTH_SHORT);
                        toastLogged.show();
                        Intent intent = new Intent(this, MainApiActivity.class);
                        startActivity(intent);
                    }
                } catch (APIErrorResponse | APICommunicationException | APIReplyParseException | APICommandConstructionException | IOException apiErrorResponse) {
                    apiErrorResponse.printStackTrace();
                    Toast toastLogged = Toast.makeText(this,"xAPI server not responding, please try later or use Db AI Trader", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }

            break;
            default:
                break;
        }
    }


}
