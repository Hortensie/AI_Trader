package com.hortensie.ai_trader.xAPI;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hortensie.ai_trader.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 2017-02-16.
 * Recycler Viewer activity that defines required adapter/manager and method that feeds viewer
 *
 */
public class SymbolRecyclerViewer extends Activity  {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_symbol);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        SymbolAdapter adapter = new SymbolAdapter(this,getSymbols());
        //SymbolAdapter adapter = new SymbolAdapter(this, fireBaseHandler.getDataFromFireBaseDb("Symbols"));
        mRecyclerView.setAdapter(adapter);
        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    //method that feeds RecyclerViewer with symbol data
    public static List<SymbolRow> getSymbols()
    {
        List<SymbolRow> data = new ArrayList<>();
        //get local copy symbol list from static variable
        List<String> symbols = FireBaseHandler.getInternalCopy();
        //icon set
        int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        for (int i=0;i<symbols.size();i++)
        {
            SymbolRow current = new SymbolRow();
            //set same icon for each row
            current.symbolId=icons[0];
            current.symbolName= symbols.get(i);
            data.add(current);
        }

        return data;
    }



}
