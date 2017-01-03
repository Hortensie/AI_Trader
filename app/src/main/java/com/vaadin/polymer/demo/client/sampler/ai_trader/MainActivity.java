package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/** Created by Piotr on 2017-01-03
 * GitFlow information about branches.
        git -c core.quotepath=false flow init -d
        Using default branch names.
        Which branch should be used for bringing forth production releases?
        - master
        Branch name for production releases: [master]
        Branch name for "next release" development: [develop]
        How to name your supporting branch prefixes?
        Feature branches? [feature/]
        Bugfix branches? [bugfix/]
        Release branches? [release/]
        Hotfix branches? [hotfix/]
        Support branches? [support/]
        Version tag prefix? []
        Hooks and filters directory? [C:/vulk_tu/AI_Trader/.git/hooks]
*/

public class MainActivity extends Activity implements View.OnClickListener {


    // Used to load the 'native-lib' library on application startup.
    /*
    static {
        System.loadLibrary("native-lib");
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // enable transitions animation
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(R.string.textview_testtext);
        Button buttonXApi = (Button) findViewById(R.id.buttonXApi);
        buttonXApi.setOnClickListener(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.

    public native String stringFromJNI();
     */

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonXApi:
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent(this,xApiLogin.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            default:
                break;
        }
    }

}
