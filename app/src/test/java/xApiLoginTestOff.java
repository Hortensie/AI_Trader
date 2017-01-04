import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiLogin;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiTradingInput;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created by Piotr on 2017-01-02.
 * basic robolectric unit tests to validate
 * 1 - login edit text
 * 2 - password edit text
 * 3 - button to start xapi trading input class
 */
@Config(sdk = Build.VERSION_CODES.LOLLIPOP,manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)

public class xApiLoginTestOff {

    private xApiLogin xApiLogin;


    @Before
    public void setupActivity(){
        xApiLogin= Robolectric.setupActivity(xApiLogin.class);
    }

    @Test
    public void validateLoginEditText(){

        EditText loginEditText = (EditText)xApiLogin.findViewById(R.id.editTextLogin);
        assertNotNull("EditText could not be found",loginEditText);
        assertTrue("EditText contains incorrect text",
                "login".equals(loginEditText.getText().toString()));
    }

    @Test
    public void validatePasswordEditText()
    {
        EditText passwordEditText =(EditText)xApiLogin.findViewById(R.id.editTextPassword);
        assertNotNull("EditText could not be found",passwordEditText);
        assertTrue("EditText contains incorrect text","password".equals(passwordEditText.getText().toString()));

    }

    @Test
    public void validateClickButtonToStartxApiTradingInputActivity()
    {
        Button getData = (Button)xApiLogin.findViewById(R.id.buttonGetData);
        getData.performClick();
        Intent intent = Shadows.shadowOf(xApiLogin).peekNextStartedActivity();
        assertEquals(xApiTradingInput.class.getCanonicalName(),intent.getComponent().getClassName());
    }

    /*
    @Test

    public void testAuthenticate() {

        xApiLogin authenticatorMock;
        //AuthenticatorApplication authenticator;
        long username = 10073026;
        String password = "devil666";
        authenticatorMock = Mockito.mock(xApiLogin.class);
        //authenticator = new AuthenticatorApplication(authenticatorMock);
        when(authenticatorMock.xApiLoginToServer(username, password)).thenReturn(false);

        boolean actual = authenticatorMock.xApiLoginToServer(username, password);
        assertFalse(actual);

    }

*/

}
