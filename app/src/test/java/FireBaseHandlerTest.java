import com.vaadin.polymer.demo.client.sampler.ai_trader.FireBaseHandler;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-20.
 */

public class FireBaseHandlerTest {

    @Test
    public void validateEncode()

    {
        String initial ="Test.1";
        String expected ="Test,1";
        assertEquals(expected, FireBaseHandler.EncodeString(initial));
    }


    @Test
    public void validateDecode()
    {
        String expected ="Test.1";
        String initial ="Test,1";
        assertEquals(expected, FireBaseHandler.DecodeString(initial));
    }
}
