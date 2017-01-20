import com.vaadin.polymer.demo.client.sampler.ai_trader.SymbolRecord;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-20.
 */

public class SymbolRecordTest {

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
