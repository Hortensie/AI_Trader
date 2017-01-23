import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiConnectionLogin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;


/**
 * Created by Piotr on 2017-01-23.
 *
 */
@RunWith(JUnit4.class)

public class xApiConnectionLoginTest2 {

    private xApiConnectionLogin xApiConnectionObject;
    private Credentials SUT;
    private SyncAPIConnector connector;


    @Before
    public void setUp(){
        xApiConnectionObject = new xApiConnectionLogin();
        long login = 10111018L;
        String password = "9d222175";
        SUT = xApiConnectionObject.setLogin(login, password);
    }

    @Test
    public void validateSetLogin() {
        Assert.assertNotNull(SUT);
    }

    @Test
    public void validateXApiLoginToServer() throws APIErrorResponse, APIReplyParseException, APICommandConstructionException, APICommunicationException, IOException {
        connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);
        LoginResponse response;
        response=xApiConnectionObject.xApiLoginToServer(SUT,connector);
        Assert.assertTrue(response.getStatus());

    }

   @Test
   public void validateSetterGetterForGlobalSync() throws IOException {
   connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);
   xApiConnectionLogin.setGlobalSyncs(connector);
   Assert.assertEquals(connector,xApiConnectionLogin.getGlobalSyncs());

   }

    @Test
    public void validateSetterGetterForGlobalSyncNull() throws IOException {
        xApiConnectionLogin.setGlobalSyncs(null);
        Assert.assertNull(xApiConnectionLogin.getGlobalSyncs());

    }

}
