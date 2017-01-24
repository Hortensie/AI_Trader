import android.graphics.Paint;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleDataSet;
import com.vaadin.polymer.demo.client.sampler.ai_trader.CandleChartDrawer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 2017-01-24.
 * Unit Tests for CandleChartDrawer using mocks
 */

public class CandleChartDrawerTest {

    private CandleStickChart candleStickChart;
    private CandleChartDrawer sut;

    @Before
    public void setUp(){

        candleStickChart = mock(CandleStickChart.class);
        sut = new CandleChartDrawer();
    }

    @Test
    public void validatePrepareXAxis(){

        XAxis xAxis = mock(XAxis.class);
        when(candleStickChart.getXAxis()).thenReturn(xAxis);
        Assert.assertNotNull(xAxis);
        sut.prepareXAxis(candleStickChart);
        xAxis=candleStickChart.getXAxis();
        Assert.assertNotNull(xAxis);
        verify(xAxis).setDrawGridLines(false);
        verify(xAxis).setTextColor(BLACK);
        verify(xAxis).setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    @Test
    public void validatePrepareYLeftAxis(){
        YAxis yAxis = mock(YAxis.class);
        //stub
        when(candleStickChart.getAxisLeft()).thenReturn(yAxis);
        Assert.assertNotNull(yAxis);
        sut.prepareYLeftAxis(candleStickChart);
        yAxis=candleStickChart.getAxisLeft();
        Assert.assertNotNull(yAxis);
        verify(yAxis).setDrawGridLines(false);
        verify(yAxis).setTextColor(BLACK);
    }

    @Test
    public void validatePrepareChart(){

        sut.prepareChart(candleStickChart);
        verify(candleStickChart).setBackgroundColor(WHITE);
        verify(candleStickChart).setMaxVisibleValueCount(60);
        verify(candleStickChart).setPinchZoom(false);
        verify(candleStickChart).setAutoScaleMinMaxEnabled(true);
    }

    @Test
    public void validateAdjustCandleSet(){
        CandleDataSet set = mock(CandleDataSet.class);
        sut.adjustCandleDataSet(set);
        verify(set).setShadowColor(BLACK);
        verify(set).setShadowWidth(0.7f);
        verify(set).setDecreasingColor(RED);
        verify(set).setDecreasingPaintStyle(Paint.Style.FILL);
        verify(set).setIncreasingColor(GREEN);
        verify(set).setIncreasingPaintStyle(Paint.Style.FILL);
    }

}
