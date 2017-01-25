import com.vaadin.polymer.demo.client.sampler.ai_trader.ChartRangeInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import pro.xstore.api.message.codes.PERIOD_CODE;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-24.
 * Tests for ChartRangeInfo.class which act as input object for retrieving financial data from xAPI server
 * Input object contain data selected from end user (symbol name, period, start time, end time)
 */
@RunWith(Parameterized.class)

public class ChartRangeInfoTest {


    private String symbol;
    private PERIOD_CODE period;
    private long start;
    private long end;
    private ChartRangeInfo chartRangeInfo;

    @Before
    public void setUp(){
        period=mock(PERIOD_CODE.class);
        chartRangeInfo = new ChartRangeInfo(symbol,period,start,end);
    }

    @Parameterized.Parameters (name = "SetObject()")
    public static Object[] getSymbolData(){

        return new Object[]
                {
                        new Object[]{"EURUSD",123456789,987654321},
                        new Object[]{"PLNJPN",987612345,123459876},
                };
    }

    public ChartRangeInfoTest(String symbol, long start, long end) {
        this.symbol = symbol;
        this.start = start;
        this.end = end;
    }

    @Test
    public void validateGetterForChartRangeInfo(){

        Assert.assertEquals(symbol,chartRangeInfo.getSymbol());
        Assert.assertEquals(start,chartRangeInfo.getStart(),0);
        Assert.assertEquals(end,chartRangeInfo.getEnd(),0);
        Assert.assertNotNull(period);

    }


    @Test
    public void validateSetterForChartRangeInfo(){

        chartRangeInfo.setEnd(7777);
        Assert.assertEquals(7777,chartRangeInfo.getEnd(),0);
        chartRangeInfo.setStart(6666);
        Assert.assertEquals(6666,chartRangeInfo.getStart(),0);
        chartRangeInfo.setPeriod(PERIOD_CODE.PERIOD_M15);
        Assert.assertEquals(PERIOD_CODE.PERIOD_M15,chartRangeInfo.getPeriod());
        chartRangeInfo.setSymbol("USPLN");
        Assert.assertEquals("USPLN",chartRangeInfo.getSymbol());



    }

}
