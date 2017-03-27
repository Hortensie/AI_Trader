package aiViewer;

import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by szczesny on 2017-03-23.
 *  Unit Test for aiViewer & customer CandleEntryRecord
 */

public class CandleEntryRecordTest {

    @Test
    public void getterForCandleEntryRecord(){

        CandleEntryRecord symbol = new CandleEntryRecord(1,2,3,4,5);
        assertEquals(1,symbol.getX(),0);
        assertEquals(2,symbol.getHigh(),0);
        assertEquals(3,symbol.getLow(),0);
        assertEquals(4,symbol.getOpen(),0);
        assertEquals(5,symbol.getClose(),0);
        assertEquals(1,symbol.getShadowRange(),0);
        assertEquals(1,symbol.getBodyRange(),0);

        assertNotNull(symbol.copy());


    }


    @Test
    public void setterForCandleEntryRecord(){

        CandleEntryRecord freshSymbol = new CandleEntryRecord();
        freshSymbol.setX(11);
        freshSymbol.setHigh(12);
        freshSymbol.setLow(13);
        freshSymbol.setOpen(14);
        freshSymbol.setClose(15);
        freshSymbol.setY(16);
        assertEquals(11,freshSymbol.getX(),0);
        assertEquals(12,freshSymbol.getHigh(),0);
        assertEquals(13,freshSymbol.getLow(),0);
        assertEquals(14,freshSymbol.getOpen(),0);
        assertEquals(15,freshSymbol.getClose(),0);
        assertEquals(16,freshSymbol.getY(),0);
    }
}
