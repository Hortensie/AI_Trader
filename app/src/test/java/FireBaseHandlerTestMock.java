import com.vaadin.polymer.demo.client.sampler.ai_trader.FireBaseHandler;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-24.
 * Test double (mocking) of FireBaseHandler.class
 */

public class FireBaseHandlerTestMock {

    private FireBaseHandler myFireBaseMock = mock(FireBaseHandler.class);

    @Test
    public void validateMocking()
    {
        Assert.assertTrue(myFireBaseMock != null);

    }






}
