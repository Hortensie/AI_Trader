package com.hortensie.ai_trader.dbTester.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.xAPI.CandleChartDrawer;
import com.hortensie.ai_trader.xAPI.xApiConnectionLogin;

/**
 * Created by szczesny on 2017-02-19.
 * Custom AppCompat activity to share Navigation View across selected activities
 */

public class CustomAppCompatActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);


        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

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
}
