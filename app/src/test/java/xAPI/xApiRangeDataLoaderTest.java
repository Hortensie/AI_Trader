package xAPI;

import android.content.Context;

import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.xAPI.ChartRangeInfo;
import com.hortensie.ai_trader.xAPI.xApiRangeDataLoader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-25.
 * Unit test for xApiRangeDataLoader.class
 */

public class xApiRangeDataLoaderTest {

    private Context mockContext;
    private ChartRangeInfo mockChartRangeInfo;
    private CandleEntryRecord candleEntry;

    @Before
    public void setUp(){

        mockContext = mock(Context.class);
        mockChartRangeInfo = mock(ChartRangeInfo.class);
        candleEntry = mock(CandleEntryRecord.class);
    }

    @Test
    public void validateConstructorChartRangeInfoTest(){

        xApiRangeDataLoader sut = new xApiRangeDataLoader(mockChartRangeInfo, mockContext);
        Assert.assertNotNull(sut);
    }

    @Test
    public void validateSetAndGetData(){

        List<CandleEntryRecord> fakeList = new ArrayList<>();
        //List<CandleEntry> spyList = Mockito.spy(fakeList);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        fakeList.add(candleEntry);
        Assert.assertEquals(3,fakeList.size());
        xApiRangeDataLoader.setDataSet(fakeList);
        Assert.assertNotNull(xApiRangeDataLoader.getDataSet());
    }

}
