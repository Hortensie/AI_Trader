package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TradingSymbol extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_symbol);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        SymbolAdapter adapter = new SymbolAdapter(this,getSymbols());
        //SymbolAdapter adapter = new SymbolAdapter(this, fireBaseDb.getDataFromFireBaseDb("Symbols"));

        mRecyclerView.setAdapter(adapter);
        //use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public static List<SymbolRow> getSymbols()
    {
        List<SymbolRow> data = new ArrayList<>();
        List<String> symbols = FireBaseDb.getInternalCopy();
        int[] icons = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        for (int i=0;i<symbols.size();i++)
        {
            SymbolRow current = new SymbolRow();
            current.symbolId=icons[0];
            current.symbolName= symbols.get(i);
            data.add(current);
        }

        return data;
    }



}
