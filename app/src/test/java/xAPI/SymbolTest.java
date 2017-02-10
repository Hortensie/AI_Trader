package xAPI;

import com.hortensie.ai_trader.xAPI.Symbol;
import com.hortensie.ai_trader.xAPI.SymbolRecord;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Piotr on 2017-01-20.
 */

public class SymbolTest {


    @Test
    public void SetterForSymbol ()

    {
        //SUT - system under test
        Symbol newSymbol = new Symbol();
        //DOC - dependency of component
        SymbolRecord symbolRecord = new SymbolRecord(1483398000000L,490500,491000,481000,491200,4372);
        //direct input
        newSymbol.setTime(123456789L);
        newSymbol.setSymbolRecord(symbolRecord);

        //direct output
        assertEquals(123456789L,newSymbol.getTime(),0);
        assertEquals(symbolRecord,newSymbol.getSymbolRecord());
        assertSame(symbolRecord,newSymbol.getSymbolRecord());
    }

    @Test
    public void GetterForSymbol ()

    {
        //DOC
        SymbolRecord symbolRecord = new SymbolRecord(1483398000000L,490500,491000,481000,491200,4372);
        //SUT
        Symbol secondSymbol = new Symbol(987654321L,symbolRecord);

        assertEquals(987654321L,secondSymbol.getTime(),0);
        assertEquals(symbolRecord,secondSymbol.getSymbolRecord());
        assertSame(symbolRecord,secondSymbol.getSymbolRecord());

    }
}
