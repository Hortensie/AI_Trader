import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiConnectionLogin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import pro.xstore.api.sync.Credentials;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Piotr on 2017-01-08.
 * Integration tests for:
 * 1) login @ xApi server (credentials)
 */
@RunWith(MockitoJUnitRunner.class)

public class xApiLoginMockito {

    @Mock
    xApiConnectionLogin xApiConnectionLogin;
    //@Mock Credentials credentials;
    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setCredentials() throws Exception
    {
        Credentials credentials=new Credentials(10111018,"9d222175");

        Mockito.when(xApiConnectionLogin.setLogin(anyLong(),anyString())).thenReturn(credentials);
        Assert.assertNotNull(xApiConnectionLogin.setLogin(10111018L,"9d222175"));
       // Mockito.verifyNoMoreInteractions(call);
    }




}
