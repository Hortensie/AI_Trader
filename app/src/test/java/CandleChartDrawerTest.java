import android.graphics.Paint;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.vaadin.polymer.demo.client.sampler.ai_trader.CandleChartDrawer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 2017-01-24.
 * Unit Tests for CandleChartDrawer using test spy / mocks
 */

public class CandleChartDrawerTest {

    private CandleStickChart candleStickChart;
    private CandleChartDrawer sut;
    private YAxis yAxis;
    private XAxis xAxis;
    private CandleDataSet set;
    private CandleEntry candleEntry;

    @Before
    public void setUp(){

        candleStickChart = mock(CandleStickChart.class);
        //System Under Test
        sut = new CandleChartDrawer();
        xAxis = mock(XAxis.class);
        yAxis = mock(YAxis.class);
        set = mock(CandleDataSet.class);
        candleEntry = mock(CandleEntry.class);
    }

    @Test
    public void validatePrepareXAxis(){
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
        sut.adjustCandleDataSet(set);
        verify(set).setShadowColor(BLACK);
        verify(set).setShadowWidth(0.7f);
        verify(set).setDecreasingColor(RED);
        verify(set).setDecreasingPaintStyle(Paint.Style.FILL);
        verify(set).setIncreasingColor(GREEN);
        verify(set).setIncreasingPaintStyle(Paint.Style.FILL);
    }

    @Test
    public void validatePrepareCandleData(){
        Assert.assertNotNull(set);
        sut.prepareCandleData(set);
        Assert.assertNotNull(set);
    }

    @Test
    public void validatePrepareCandleDataSet(){
        List<CandleEntry> fakeList = new ArrayList<>();
        //List<CandleEntry> spyList = Mockito.spy(fakeList);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        Assert.assertEquals(3,fakeList.size());

        String label ="label";
        Assert.assertNotNull(label);
        sut.prepareCandleDataSet(fakeList,label);
        Assert.assertNotNull(sut.prepareCandleDataSet(fakeList,label));
    }

    @Test
    public void validateDrawCandleChart(){
        //DoC - Dependency on Component
        //Indirect inputs
        when(candleStickChart.getXAxis()).thenReturn(xAxis);
        when(candleStickChart.getAxisLeft()).thenReturn(yAxis);

        List<CandleEntry> fakeList = new ArrayList<>();
        //List<CandleEntry> spyList = Mockito.spy(fakeList);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        Assert.assertEquals(3,fakeList.size());

        String label ="label";

        Assert.assertNotNull(label);
        Assert.assertNotNull(fakeList);
        Assert.assertNotNull(candleStickChart);
        //direct inputs to system under test (CandleChartDrawer class)
        sut.drawCandleChart(candleStickChart,fakeList,label);
        //indirect output
        verify(candleStickChart).invalidate();
    }

}
