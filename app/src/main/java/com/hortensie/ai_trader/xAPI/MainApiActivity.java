package com.hortensie.ai_trader.xAPI;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hortensie.ai_trader.R;

/** Created by Piotr on 2017-01-26
 * Main activity when user decides to login to xAPI server and get historical financial data
 * User (Admin) can get symbols from xApi
 * User (Admin) can get symbols from FireBase
 * User can select period time
 */
public class MainApiActivity extends Activity implements View.OnClickListener {

    FireBaseHandler fireBaseHandler = new FireBaseHandler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_live);
        Button buttonPeriodSelector = (Button)findViewById(R.id.buttonPeriodSelector);
        buttonPeriodSelector.setOnClickListener(this);
        //button for getting trading symbols from xAPI server
        Button buttonGetSymbols = (Button)findViewById(R.id.buttonGetSymbols);
        buttonGetSymbols.setOnClickListener(this);
        //button for getting trading symbols from FireBase database
        Button buttonGetSymbolsApi = (Button) findViewById(R.id.buttonGetSymbolsApi);
        buttonGetSymbolsApi.setOnClickListener(this);
        Log.d("json","Symbol data received from FireBase");
        //fireBaseHandler.getDataFromFireBaseDb("Symbols");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //set period time
            case R.id.buttonPeriodSelector:
                Intent setPeriod = new Intent(this,PeriodSelector.class);
                startActivity(setPeriod);
                break;
            //get symbols from FireBase
            case R.id.buttonGetSymbolsApi:
                fireBaseHandler.getDataFromFireBaseDb("Symbols");
                Toast toastSymbols = Toast.makeText(getApplicationContext(),"Symbols were received from database", Toast.LENGTH_SHORT);
                toastSymbols.show();
                break;
            //get symbols from xAPI server in new AsyncTask
            case R.id.buttonGetSymbols:
                if(xApiConnectionLogin.getGlobalSyncs()==null)
                {
                    Toast toastLogged = Toast.makeText(getApplicationContext(),"Server connection lost, re-login", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }
                else
                {
                    new xApiSymbolLoader(this).execute(xApiConnectionLogin.getGlobalSyncs());
                }
                break;

            default:
                break;
        }
    }
}
