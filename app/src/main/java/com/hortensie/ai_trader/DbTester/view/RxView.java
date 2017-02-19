package com.hortensie.ai_trader.dbTester.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hortensie.ai_trader.dbTester.presenter.RxPresenter;
import com.hortensie.ai_trader.dbTester.presenter.RxPresenterInterface;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.xAPI.CandleChartDrawer;
import com.hortensie.ai_trader.xAPI.xApiConnectionLogin;

/**
 * Created by Piotr on 2017-01-30.
 * Layer that displays data and reacts to user actions. This could be an Activity, a Fragment, an android.view.View or a Dialog.
 */

public class RxView extends AppCompatActivity implements RxViewInterface{

    TextView textView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);
        textView = (TextView) findViewById(R.id.rxJava);
        //create RxPresenter object via Interface with connection to current (this) view
        RxPresenterInterface rxPresenterInterface2 = new RxPresenter(this);
        //call showData method from rxPresenter interface
        rxPresenterInterface2.showData();

        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    // Set behavior of Navigation drawer
    navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
        // This method will trigger on item Click of navigation menu
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            // Set item in checked state
            menuItem.setChecked(true);
            switch (menuItem.getItemId()){
                case R.id.xAPI_menu:
                    startActivity(new Intent(getApplicationContext(),xApiConnectionLogin.class));
                    break;
                case R.id.dBViewer_menu:
                    startActivity(new Intent(getApplicationContext(),CandleChartDrawer.class));
                    break;
                case R.id.dBTester_menu:
                    startActivity(new Intent(getApplicationContext(),RxView.class));
                    break;
                default:
                    break;
            }
            // Closing drawer on item click
            mDrawerLayout.closeDrawers();
            return true;
        }
    });
}

    @Override
    //method that update textView widget on current view
    public void updateUi(String s) {
        textView.setText(s); // Change a View
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.xAPI_menu:
                startActivity(new Intent(getApplicationContext(),xApiConnectionLogin.class));
                break;
            case R.id.dBViewer_menu:
                startActivity(new Intent(getApplicationContext(),CandleChartDrawer.class));
                break;
            case R.id.dBTester_menu:
                startActivity(new Intent(getApplicationContext(),RxView.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}