import com.hortensie.ai_trader.SymbolRecord;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-22.
 * unit test that demonstrates Parametrized usage
 * by defining parameters with few samples
 */

/**
 * Created by Piotr on 2017-01-20.
 * Unit test for SymbolRecord class
 * sample of parametrized test in order to test few symbol record objects at once
 */
@RunWith(value = Parameterized.class)
public class SymbolRecordTest {

    @Parameters(name = "getSymbol()" )
    public static Object[] getSymbol()
    {
            return new Object[]
                    {
                            new Object[]{1483398000000L,490500,491000,481000,491200,4372},
                            new Object[]{1483398000000L,490500,491000,481000,491200,0}
                    };
    }

    private long ctm;
    private double open;
    private double high;
    private double low;
    private double close;
    private double vol;

    public SymbolRecordTest(long ctm, double open, double high, double low, double close, double vol) {
        this.ctm = ctm;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol;
    }

    @Test
    public void parametersGetterForSymbolRecord()
    {
        //SymbolRecord symbolRecord = new SymbolRecord();
        SymbolRecord symbol = new SymbolRecord(ctm,open,high,low,close,vol);
        assertEquals(ctm,symbol.getCtm());
        assertEquals(open,symbol.getOpen(),0);
        assertEquals(high,symbol.getHigh(),0);
        assertEquals(low,symbol.getLow(),0);
        assertEquals(close,symbol.getClose(),0);
        assertEquals(vol,symbol.getVol(),0);


    }

    @Test
    public void GetterForSymbolRecord(){

        SymbolRecord symbolRecord = new SymbolRecord(1483398000000L,490500,491000,481000,491200,4372);

        assertEquals(1483398000000L,symbolRecord.getCtm());
        assertEquals(490500,symbolRecord.getOpen(),0);
        assertEquals(491000,symbolRecord.getHigh(),0);
        assertEquals(481000,symbolRecord.getLow(),0);
        assertEquals(491200,symbolRecord.getClose(),0);
        assertEquals(4372,symbolRecord.getVol(),0);
    }

    @Test
    public void SetterForSymbolRecord(){

        SymbolRecord symbol = new SymbolRecord();
        symbol.setVol(4373);
        symbol.setOpen(490500);
        symbol.setHigh(490501);
        symbol.setLow(490502);
        symbol.setClose(490503);
        symbol.setCtm(1483398000000L);

        assertEquals(4373,symbol.getVol(),0);
        assertEquals(1483398000000L,symbol.getCtm());
        assertEquals(490500,symbol.getOpen(),0);
        assertEquals(490501,symbol.getHigh(),0);
        assertEquals(490502,symbol.getLow(),0);
        assertEquals(490503,symbol.getClose(),0);
        assertEquals(4373,symbol.getVol(),0);
    }

}
