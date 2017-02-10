package xAPI;

import com.hortensie.ai_trader.xAPI.ChartRangeInfo;
import com.hortensie.ai_trader.xAPI.SymbolData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-24.
 * Tests for SymbolData.class which act as value when saving data for FireBase database
 */
@RunWith(Parameterized.class)

public class SymbolDataTest {


    private long ctm;
    private double vol;
    private ChartRangeInfo chartRangeInfo;

    @Before
    public void setUp(){

        chartRangeInfo=mock(ChartRangeInfo.class);
    }

    @Parameterized.Parameters (name = "SetObject()")
    public static Object[] getSymbolData(){

        return new Object[]
                {
                        new Object[]{1234567890,2345},
                        new Object[]{987654321,5432},
                };
    }

    public SymbolDataTest(long ctm, double vol) {
        this.ctm = ctm;
        this.vol = vol;
    }

    @Test
    public void validateGetterForSymbolData(){

        SymbolData symbolData = new SymbolData(ctm,vol,chartRangeInfo);
        Assert.assertEquals(ctm,symbolData.getTime(),0);
        Assert.assertEquals(vol,symbolData.getVol(),0);
        Assert.assertNotNull(chartRangeInfo);

    }


    @Test
    public void validateSetterForSymbolData(){
        SymbolData symbolData2 = new SymbolData();
        symbolData2.setTime(1234567890L);
        symbolData2.setVol(2345);
        symbolData2.setChartRangeInfo(chartRangeInfo);
        Assert.assertEquals(1234567890L,symbolData2.getTime(),0);
        Assert.assertEquals(2345,symbolData2.getVol(),0);
        Assert.assertNotNull(chartRangeInfo);
        Assert.assertEquals(chartRangeInfo,symbolData2.getChartRangeInfo());


    }

}
