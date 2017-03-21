package com.hortensie.ai_trader.aiViewer.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleData;
import com.hortensie.ai_trader.aiViewer.presenter.CandleDrawer;
import com.hortensie.ai_trader.aiViewer.presenter.CandleDrawerInterface;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.dbTester.view.DetailActivityView;

import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

/**
 * Created by szczesny on 2017-03-20.
 * This class show Candle Data
 */

public class CandleView extends AppCompatActivity implements CandleViewInterface {

    CandleStickChart candleStickChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candlechart);
        Log.d("RxJava","Before candle stick");
        candleStickChart = (CandleStickChart)findViewById(R.id.candleChart);
        FireBaseCandleData model = new FireBaseCandleData();
        CandleDrawerInterface presenter = new CandleDrawer(model,this);
        Log.d("RxJava decode", ListContentAdapterPresenter.getTemp_symbol());
        presenter.showChartData(ListContentAdapterPresenter.getTemp_symbol(),"PERIOD_CODE +code=1440-");

        // Adding Floating Action Button to bottom right of main view
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "Hello Chart!",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }


    //method responsible for setting x axis (position, color, grids)
    public XAxis prepareXAxis(CandleStickChart candleStickChart){
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(BLACK);
        //xAxis.setAxisMaximum(30f);
        xAxis.setDrawGridLines(false);
        return xAxis;
    }

    //method responsible for setting Y Left axis (grid, color)
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

    //method that prepare candle chart (background color etc)
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

    public CandleDataSet prepareCandleDataSet(List<CandleEntry> data, String label){
        return new CandleDataSet(data,label);
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

    @Override
    public void updateChartOnUi(List<CandleEntry> candleEntries, String label) {
        prepareChart(candleStickChart);
        prepareXAxis(candleStickChart);
        prepareYLeftAxis(candleStickChart);
        candleStickChart.setData(prepareCandleData(prepareCandleDataSet(candleEntries,label)));
        candleStickChart.invalidate();
    }
}
