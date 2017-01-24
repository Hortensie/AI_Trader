import com.vaadin.polymer.demo.client.sampler.ai_trader.FireBaseHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-20.
 *
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
}
