package xAPI;

import com.hortensie.ai_trader.xAPI.xApiConnectionLogin;

import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;

import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.SyncAPIConnector;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Piotr on 2017-01-23.
 *
 */

public class xApiConnectionLoginTest {

    private xApiConnectionLogin xApiConnectionObject;
    private Credentials mockCredentials;
    private SyncAPIConnector connector;
    private LoginResponse response;

    @Before
    public void setUp(){
        xApiConnectionObject = new xApiConnectionLogin();
        response = mock(LoginResponse.class);
        connector = mock(SyncAPIConnector.class);
        mockCredentials=mock(Credentials.class);
    }

    @org.junit.Test
    public void validateSetLogin() {
        String password = "9d222175";
        long login = 10111018L;
        xApiConnectionObject.setLogin(login, password);
        Assert.assertNotNull(mockCredentials);
    }

    @org.junit.Test
    public void validateXApiLoginToServer() throws APIErrorResponse, APIReplyParseException, APICommandConstructionException, APICommunicationException, IOException {

        try {
            response=xApiConnectionObject.xApiLoginToServer(mockCredentials,connector);
            verify(response).getStatus();
        }
        catch (APIErrorResponse | APICommunicationException | APIReplyParseException | APICommandConstructionException | IOException apiErrorResponse) {
            apiErrorResponse.printStackTrace();
        }
    }

   @org.junit.Test
   public void validateSetterGetterForGlobalSync() throws IOException {
   xApiConnectionLogin.setGlobalSyncs(connector);
   Assert.assertEquals(connector,xApiConnectionLogin.getGlobalSyncs());

   }

    @org.junit.Test
    public void validateSetterGetterForGlobalSyncNull() throws IOException {
        xApiConnectionLogin.setGlobalSyncs(null);
        Assert.assertNull(xApiConnectionLogin.getGlobalSyncs());

    }

}
