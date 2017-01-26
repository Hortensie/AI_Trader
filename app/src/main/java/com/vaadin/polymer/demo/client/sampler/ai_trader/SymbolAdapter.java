package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Piotr on 2016-12-30.
 *
 */

class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<SymbolRow> data = Collections.emptyList();

    SymbolAdapter(Context context, List<SymbolRow> data) {
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //xml file that represents single row and inflate it
        View view = inflater.inflate(R.layout.symbol_row,parent,false);
        //pass it to view holder to avoid findViewById every time
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        //get current position of the object
        final SymbolRow current = data.get(position);
        holder.title.setText(current.symbolName);
        holder.icon.setImageResource(current.symbolId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext()," SymbolRecord: "+current.symbolName +" selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),xApiUiInput.class);
                intent.putExtra("symbol",current.symbolName);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }
    }
}
