package xAPI;

import com.hortensie.ai_trader.xAPI.FireBaseHandler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-20.
 * Parametrized unit tests for FIreBaseHandler.class
 * Test of Decode / Encode functions
 */
@RunWith(Parameterized.class)

public class FireBaseHandlerTest {

    private String input;
    private String expected;

    @Parameterized.Parameters(name = "DecEnd")
    public static Iterable<Object[]> data()
    {
        return Arrays.asList(new Object[][]
        {
                {"Test.1","Test,1"},
                {".Test2",",Test2"},
                {".Test3.",",Test3,"},
                {"[Test4]","+Test4-"}
        }
        );
    }

    public FireBaseHandlerTest(String input,String expected)
    {
        this.input=input;
        this.expected=expected;
    }

    @Test
    public void validateEncodeParameterString()
    {
        assertEquals(expected,FireBaseHandler.EncodeString(input));
    }

    @Test
    public void validateDecodeParameterString()
    {
        assertEquals(input,FireBaseHandler.DecodeString(expected));
    }

    @Test
    public void validateGetInternalCopy(){

        Assert.assertEquals(0,FireBaseHandler.getInternalCopy().size());
        Assert.assertNotNull(FireBaseHandler.getInternalCopy());
    }

}
