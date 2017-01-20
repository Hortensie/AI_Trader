package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

/**
 * Created by Piotr on 2017-01-19.
 * class that uses MPAndroidChart Library in order to draw Candle Chart
 */

public class CandleChartDrawer extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candlechart);


        CandleStickChart candleStickChart = (CandleStickChart)findViewById(R.id.candleChart);
        //set background color
        candleStickChart.setBackgroundColor(BLACK);
        //turn off grid on chart
        //candleStickChart.setDrawGridBackground(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        //candleStickChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        candleStickChart.setPinchZoom(false);
        //If enabled the y axis automatically adjusts to the min and max y values
        // of the current x axis range whenever the viewport changes.
        candleStickChart.setAutoScaleMinMaxEnabled(true);

        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(WHITE);
        xAxis.setAxisMaximum(30f);
        xAxis.setDrawGridLines(false);
        //setting this to true will activate chart logcat output
        //xAxis.setEnabled(false);
        //y
        YAxis leftAxis = candleStickChart.getAxisLeft();
        //set maximum value for x (zoom)
        leftAxis.setAxisMaximum(30f);
        //YAxis rightAxis = candleStickChart.getAxisRight();
        leftAxis.setDrawGridLines(false);
        //rightAxis.setDrawGridLines(false);
        //remove yAxis from right side of screen
        //rightAxis.setEnabled(false);
        //leftAxis.setEnabled(false);

        leftAxis.setTextColor(WHITE);
       // rightAxis.setTextColor(WHITE);

        ArrayList<CandleEntry> entries = new ArrayList<>();
        entries.add(new CandleEntry(1,10,1,4,5));
        entries.add(new CandleEntry(2,9,2,3,6));
        entries.add(new CandleEntry(3,20,15,11,8));
        entries.add(new CandleEntry(4,12,4,5,6));
        entries.add(new CandleEntry(5,10,1,4,5));
        entries.add(new CandleEntry(6,10,1,4,5));
        entries.add(new CandleEntry(7,9,2,3,6));
        entries.add(new CandleEntry(8,8,1,3,4));
        entries.add(new CandleEntry(9,22,18,16,17));
        entries.add(new CandleEntry(10,10,1,4,5));
        entries.add(new CandleEntry(11,12,1,4,2));
        entries.add(new CandleEntry(12,18,16,13,6));
        entries.add(new CandleEntry(13,8,1,3,4));
        entries.add(new CandleEntry(14,12,4,5,3));
        entries.add(new CandleEntry(15,10,1,4,1));
        entries.add(new CandleEntry(16,10,1,4,5));


        CandleDataSet dataSet = new CandleDataSet(entries,"# of Calls");
        //dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        //set candle color
        //dataSet.setColor(WHITE);
        //line color inside candler
        dataSet.setShadowColor(WHITE);
        dataSet.setShadowWidth(0.7f);
        //
        dataSet.setDecreasingColor(RED);
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        //candle color around bar
        dataSet.setIncreasingColor(GREEN);
        dataSet.setIncreasingPaintStyle(Paint.Style.FILL);

        //
        //dataSet.setNeutralColor(BLUE);

        //defining X-axis labels
        //ArrayList<String> labels = new ArrayList<>();
        //labels.add("czas");


        CandleData candleData = new CandleData(dataSet);
        candleStickChart.setData(candleData);
        candleStickChart.invalidate();




    }



}
