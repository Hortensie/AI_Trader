package com.hortensie.ai_trader.xAPI;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.R;

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
        if(xApiRangeDataLoader.getDataSet()!=null&&xApiRangeDataLoader.getDataSet().size()!=0) {
            drawCandleChart(candleStickChart, xApiRangeDataLoader.getDataSet(), "label");
        }
        else {
            Toast toastLogged = Toast.makeText(this,"There is no data to draw, get first from db!", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
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

    public void drawCandleChart(CandleStickChart candleStickChart, List<CandleEntry> dataList, String label )
    {
        prepareChart(candleStickChart);
        prepareXAxis(candleStickChart);
        prepareYLeftAxis(candleStickChart);
        candleStickChart.setData(prepareCandleData(prepareCandleDataSet(dataList,label)));
        candleStickChart.invalidate();
    }

    public CandleDataSet prepareCandleDataSet(List<CandleEntry> data, String label){
        return  new CandleDataSet(data,label);
    }

    public CandleData prepareCandleData(CandleDataSet dataSet)
    {
        //add colors & shadows for specific data set
        adjustCandleDataSet(dataSet);
        return new CandleData(dataSet);
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
