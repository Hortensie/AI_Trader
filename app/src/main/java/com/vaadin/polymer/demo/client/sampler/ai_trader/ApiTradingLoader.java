package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Credentials;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import info.guardianproject.netcipher.NetCipher;

import pro.xstore.api.sync.Connector;
import static android.R.attr.data;

/**
 * Created by Piotr on 2016-12-01.
 */

public class ApiTradingLoader extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {

        String endResult="";
        String apiAddress = params[0];


        try {
            //URL url = new URL(apiAddress);
            //Login arguments = new Login("10073026", "devil666");
            //TopLogin topLogin = new TopLogin("login", arguments);


            Log.d("json", apiAddress);
            //accepting certification
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {

                private X509Certificate[] accepted;

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    accepted = x509Certificates;
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return accepted;
                }
            }}, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            //end of accepting certification
            HttpsURLConnection httpsURLConnection = NetCipher.getHttpsURLConnection(apiAddress);
            //net.ssl.SSLHandshakeException: javax.net.ssl.SSLProtocolException: SSL handshake aborted
            //HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpsURLConnection.setRequestProperty("Connection", "close");
            httpsURLConnection.setRequestMethod("POST");

            //httpsURLConnection.setFixedLengthStreamingMode(107);
            //httpsURLConnection.setRequestProperty("Accept-Encoding", "identity");

            Log.d("json", "proba otwarcia polaczenia");
            Log.d("json","executing httpsURLconnection.connect()");
            httpsURLConnection.connect();

            JSONObject command = new JSONObject();
            command.put("command","login");
            JSONObject arguments = new JSONObject();
            arguments.put("userId","10073026");
            arguments.put("password","devil666");
        //  arguments.put("appId","test");
            arguments.put("appName","test");
            command.put("arguments",arguments);


            //ObjectOutputStream objwriter = new ObjectOutputStream(httpsURLConnection.getOutputStream());
            //output writer - send data to SSL https
            OutputStreamWriter writer = new OutputStreamWriter(httpsURLConnection.getOutputStream());
            writer.write(command.toString());

            //objwriter.writeObject(command);
            Log.d("json","wyslano json object");
            Log.d("json request",command.toString());
            writer.flush();
            Log.d("json","Flushed");
            //son gsonObject = new Gson();
          //  Arguments arguments = new Arguments("10073026", "devil666","test","test");
           // Login login = new Login("login",arguments);
          //  Log.d("json", login.toString());
           // OutputStreamWriter writer = new OutputStreamWriter(httpsURLConnection.getOutputStream());
          //  Log.d("json(Request)", gsonObject.toJson(login));
           // writer.write(gsonObject.toJson(login));
          //  Log.d("json", "wyslano Json request");

            //ObjectInputStream objectInput = (ObjectInputStream) httpsURLConnection.getInputStream();
             //  Log.d("json","input+"+objectInput.toString());
            //int responseCodeHTTP = httpsURLConnection.getResponseCode();
            // Log.d("json",""+responseCodeHTTP);

            JsonReader reader = new JsonReader(new InputStreamReader(httpsURLConnection.getInputStream()));
           // BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            Log.d("json","wyslano json response ");
            System.out.println(reader);
            /*StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = reader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            endResult = responseStrBuilder.toString();
            //InputStream input = httpsURLConnection.getInputStream();
          //  Log.d("json", input.toString());

            writer.flush();
            writer.close();
            //objwriter.close();
            Log.d("json", "object writer close");

            /*
            InputStream input = httpsURLConnection.getInputStream();
            Log.d("json","tworzenie bufora do odebrania danych - buffer reader");


            if (httpsURLConnection.getResponseCode() == 200) {
                Log.d("json", "connection is OK");
                Certificate[] certificates = httpsURLConnection.getServerCertificates();
                for (int i = 0; i < certificates.length; i++) {
                    Certificate certificate = certificates[i];
                    File file = new File("/tmp/newcert_" + i + ".crt");
                    byte[] buf = certificate.getEncoded();

                    FileOutputStream os = new FileOutputStream(file);
                    os.write(buf);
                    os.close();
                }

            } else
            {
                int outputmessage = httpsURLConnection.getResponseCode();
                Log.d("response code", ""+outputmessage);
            }





            */
           // response = new JSONObject(result.toString());
           // copyInputStreamToOutputStream(input, System.out);*/
            //Log.d("json api","https connection disconnect");
           // httpsURLConnection.disconnect();

       } catch (Exception e) {
          Log.d("json","IO exception oco chodzi");
          e.printStackTrace();
        }
        finally {
            Log.d("json api","finally");
        }

        return endResult;
    }

    @Override
    protected void onPostExecute(String  result_fromDoInBackground) {
       // Log.d("input",result_fromDoInBackground.toString());
    //    Gson gson = new Gson();
       /* Bar newBar = gson.fromJson(result_fromDoInBackground,Bar.class);
        FirebaseDb firebaseDb = new FirebaseDb();
        firebaseDb.saveData("Ask",newBar.getAsk());
        firebaseDb.saveData("Bid",newBar.getBid());
        firebaseDb.saveData("Last",newBar.getLast());
        firebaseDb.saveData("Low",newBar.getLow());
        firebaseDb.saveData("High",newBar.getHigh());
        firebaseDb.saveData("Vwap",newBar.getVwap());
        firebaseDb.saveData("Volume",newBar.getVolume());*/

        //Euro newEuro = gson.fromJson(result_fromDoInBackground,Euro.class);
      //  FirebaseDb firebaseDb = new FirebaseDb();
      //  firebaseDb.saveData(newEuro.getEffectiveDate(),"Date",newEuro.getEffectiveDate());
     //   firebaseDb.saveData(newEuro.getEffectiveDate(),"MidPrice",newEuro.getMid());


    }

    public class NullHostNameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            Log.i("RestUtilImpl", "Approving certificate for " + hostname);
            return true;
        }

    }
}
