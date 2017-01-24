import com.vaadin.polymer.demo.client.sampler.ai_trader.SymbolData;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by Piotr on 2017-01-24.
 */
@RunWith(Parameterized.class)

public class SymbolDataTest {

    @Parameterized.Parameters (name = "SetObject()")
    public static Object[] getSymbolData(){

        return new Object[]
                {
                     new Object[]{1234567890,2345},
                     new Object[]{987654321,5432},
                };
    }

    private long ctm;
    private double vol;

    public SymbolDataTest(long ctm, double vol) {
        this.ctm = ctm;
        this.vol = vol;
    }

    @Test
    public void validateGetterForSymbolData(){
        SymbolData symbolData = new SymbolData(ctm,vol);
        Assert.assertEquals(ctm,symbolData.getTime(),0);
        Assert.assertEquals(vol,symbolData.getVol(),0);

    }

    @Test
    public void validateSetterForSymbolData(){
        SymbolData symbolData2 = new SymbolData();
        symbolData2.setTime(1234567890L);
        symbolData2.setVol(2345);
        Assert.assertEquals(1234567890L,symbolData2.getTime(),0);
        Assert.assertEquals(2345,symbolData2.getVol(),0);

    }

}
