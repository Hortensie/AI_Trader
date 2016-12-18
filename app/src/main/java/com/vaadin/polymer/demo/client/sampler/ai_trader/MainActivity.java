package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.SymbolRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


   // FirebaseDb myDB=new FirebaseDb();
    //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
   // DatabaseReference databaseReference = firebaseDatabase.getReference();
    //final String API ="https://www.bitmarket.pl/graphs/BTCPLN/1y.json";
    //final String API ="http://dev.superman-academy.com/api.php";
    //final String API ="https://www.bitmarket.pl/json/BTCPLN/ticker.json";

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
        Button loginStream = (Button) findViewById(R.id.buttonStream);
        buttonLogin.setOnClickListener(this);
        //EditText dateInput = (EditText) findViewById(R.id.e);
        EditText timeInput = (EditText) findViewById(R.id.editTextTime);
       // EditText symbolInput = (EditText) findViewById(R.id.editTextSymbol);
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       // StrictMode.setThreadPolicy(policy);


    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void onClick(View view) {

        //call AsyncTask in order to login and retrieve data from xAPI and then save it @Firebase Database
        new xApiTradingLoader().execute();
    }

    public void streamLoader(View view) {
        new xApiTradingLoader().execute();
    }
}
