package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static pro.xstore.api.message.codes.PERIOD_CODE.PERIOD_D1;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputLogin, inputPassword;
    xApiLogin xApiLogin;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Button buttonStream = (Button) findViewById(R.id.buttonStream);
        inputLogin = (EditText) findViewById(R.id.editTextLogin);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(this);
        buttonStream.setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonLogin:
                //call AsyncTask in order to login and retrieve data from xAPI and then save it @Firebase Database
                long login = Long.parseLong(inputLogin.getText().toString());
                xApiLogin = new xApiLogin(getApplicationContext());
                xApiLogin.xApiLoginToServer(login, inputPassword.getText().toString());
                break;
            case R.id.buttonStream:
                new xApiTradingLoader(xApiLogin.getGlobalSyncs(),"EURUSD",PERIOD_D1,1451660400000L,1483196400000L).execute();
                break;
            default:
                Log.d("error","Buttons are not working");
                break;
        }
    }


}
