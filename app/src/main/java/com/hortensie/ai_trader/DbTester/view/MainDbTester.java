package com.hortensie.ai_trader.dbTester.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.aiViewer.view.CandleView;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment;
import com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragmentInterface;
import com.hortensie.ai_trader.dbTester.view.Fragments.CardContentFragment;
import com.hortensie.ai_trader.dbTester.view.Fragments.TileContentFragment;
import com.hortensie.ai_trader.xAPI.CandleChartDrawer;
import com.hortensie.ai_trader.xAPI.xApiConnectionLogin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Szczesny on 2017-02-16.
 * Main application view - initial view
 * This Class / Activity provides following functions:
 * - review / selection of financial symbol
 * - selection of all application functions (viewer, xAPi etc)
 */

public class MainDbTester extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private FireBaseModelInterface modelInterface=new FireBaseModel(firebaseDatabase.getReference(),firebaseDatabase);
    private ListContentFragmentInterface listContentFragmentInterface;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aitrader);
       // adapter = new ListContentAdapterPresenter(getApplicationContext(),modelInterface.getListSymbolRecordFromFireBase("ListSymbolRecords"));
        listContentFragmentInterface=new ListContentAdapterPresenter(getApplicationContext(),modelInterface.getSymbolListFromFireBase("ListSymbolRecords"));

        //allow network connection for xAPI login
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            //as Tint might product null point exception assert it
            assert indicator != null;
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()){
                            case R.id.xAPI_menu:
                                startActivity(new Intent(getApplicationContext(),xApiConnectionLogin.class));
                                break;
                            case R.id.dBViewer_menu:
                                startActivity(new Intent(getApplicationContext(),CandleView.class));
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
        // Adding Floating Action Button to bottom right of main view
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Hello SnackBar!",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CardContentFragment(), "Features");
        adapter.addFragment(new TileContentFragment(), "Tile");
        adapter.addFragment(new com.hortensie.ai_trader.dbTester.view.Fragments.ListContentFragment(), "Symbols");
        viewPager.setAdapter(adapter);
    }

    private static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //add search menu
        final MenuItem search = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        //listContentFragmentInterface.search(searchView);
        return true;
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
                startActivity(new Intent(getApplicationContext(),CandleView.class));
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
