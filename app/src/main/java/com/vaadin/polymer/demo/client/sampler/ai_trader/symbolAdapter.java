package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Piotr on 2016-12-30.
 */

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<SymbolRow> data = Collections.emptyList();


    public SymbolAdapter(Context context, List<SymbolRow> data) {
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //xml file that represents single row and inflate it
        View view = inflater.inflate(R.layout.symbol_row,parent,false);
        //pass it to view holder to avoid findViewById every time
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //get current position of the object
        SymbolRow current = data.get(position);
        holder.title.setText(current.symbolName);
        holder.icon.setImageResource(current.symbolId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

                //getPosition();
        }
    }
}
