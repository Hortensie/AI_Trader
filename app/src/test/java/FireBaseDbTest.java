import com.vaadin.polymer.demo.client.sampler.ai_trader.FireBaseDb;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Piotr on 2017-01-20.
 */

public class FireBaseDbTest {

    @Test
    public void validateEncode()

    {
        String initial ="Test.1";
        String expected ="Test,1";
        assertEquals(expected, FireBaseDb.EncodeString(initial));
    }


    @Test
    public void validateDecode()
    {
        String expected ="Test.1";
        String initial ="Test,1";
        assertEquals(expected, FireBaseDb.DecodeString(initial));
    }
}
