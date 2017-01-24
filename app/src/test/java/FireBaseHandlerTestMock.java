import com.vaadin.polymer.demo.client.sampler.ai_trader.FireBaseHandler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pro.xstore.api.message.records.RateInfoRecord;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 2017-01-24.
 * Test double (mocking) of FireBaseHandler.class
 */
@RunWith(JUnit4.class)
public class FireBaseHandlerTestMock {

    private FireBaseHandler myFireBaseMock = mock(FireBaseHandler.class);
    private RateInfoRecord myRateInfoRecord = mock(RateInfoRecord.class);

    @Test
    public void validateMocking()
    {
        Assert.assertTrue(myFireBaseMock != null);

    }

    @Test
    public void testDefaultValue()
    {
        Assert.assertFalse("new test double should return false as boolean", myFireBaseMock.test());
    }

    @Test
    public void testStubbing()
    {
        when(myFireBaseMock.test()).thenReturn(true);
        Assert.assertTrue("new test double should return false as boolean", myFireBaseMock.test());
    }


    @Test (expected = RuntimeException.class)
    public void throwException(){
        when(myFireBaseMock.test()).thenThrow(new RuntimeException());
        myFireBaseMock.test();

    }

    @Test
    public void testVerification()
    {
        myFireBaseMock.test();
        verify(myFireBaseMock).test();
    }



}
