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

import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

/**
 * Created by Piotr on 2017-01-19.
 * class that uses MPAndroidChart Library in order to draw Candle Chart
 * Main function drawCandleChart receive CandleEntry List to draw Candle Chart
 */

public class CandleChartDrawer extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candlechart);
        CandleStickChart candleStickChart = (CandleStickChart)findViewById(R.id.candleChart);
        drawCandleChart(candleStickChart,xApiRangeDataLoader.getDataSet());
    }

    public XAxis prepareXAxis(CandleStickChart candleStickChart){
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(BLACK);
        //xAxis.setAxisMaximum(30f);
        xAxis.setDrawGridLines(false);
        return xAxis;
    }

    public YAxis prepareYLeftAxis(CandleStickChart candleStickChart){
        YAxis leftAxis = candleStickChart.getAxisLeft();
        //set maximum value for x (zoom)
        //leftAxis.setAxisMaximum(30f);
        //YAxis rightAxis = candleStickChart.getAxisRight();
        leftAxis.setDrawGridLines(false);
        //rightAxis.setDrawGridLines(false);
        //remove yAxis from right side of screen
        //rightAxis.setEnabled(false);
        //leftAxis.setEnabled(false);
        leftAxis.setTextColor(BLACK);
        // rightAxis.setTextColor(WHITE);
        return leftAxis;
    }

    public void prepareChart(CandleStickChart candleStickChart){
        candleStickChart.setBackgroundColor(WHITE);
        //turn off grid on chart
        //candleStickChart.setDrawGridBackground(false);
        // if more than 60 entries are displayed in the chart, no values will be drawn
        candleStickChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        candleStickChart.setPinchZoom(false);
        //If enabled the y axis automatically adjusts to the min and max y values
        // of the current x axis range whenever the viewport changes.
        candleStickChart.setAutoScaleMinMaxEnabled(true);
    }

    public void drawCandleChart(CandleStickChart candleStickChart, List<CandleEntry> dataList )
    {
        prepareChart(candleStickChart);
        prepareXAxis(candleStickChart);
        prepareYLeftAxis(candleStickChart);
        candleStickChart.setData(prepareCandleData(dataList));
        candleStickChart.invalidate();
    }

    public CandleData prepareCandleData(List<CandleEntry> data){
        CandleDataSet dataSet = new CandleDataSet(data,"# of Calls");
        CandleData candleData = new CandleData(dataSet);
        adjustCandleDataSet(dataSet);
        return  candleData;
    }

    public void adjustCandleDataSet(CandleDataSet dataSet){
        //dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        //set candle color
        //dataSet.setColor(WHITE);
        //line color inside candler
        dataSet.setShadowColor(BLACK);
        dataSet.setShadowWidth(0.7f);
        dataSet.setDecreasingColor(RED);
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        //candle color around bar
        dataSet.setIncreasingColor(GREEN);
        dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        //dataSet.setNeutralColor(BLUE);
    }

}
