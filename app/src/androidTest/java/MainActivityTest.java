/**
 * Created by Piotr on 2017-01-02.
 * Robolectric local tests for MainActivity.class
 */


import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
// Static imports for assertion methods
import com.vaadin.polymer.demo.client.sampler.ai_trader.MainActivity;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiLogin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@Config(sdk = Build.VERSION_CODES.LOLLIPOP,manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)


public class MainActivityTest {

    private MainActivity activity;

    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    public void setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    public void validateTextViewContent() {

        TextView tvHelloWorld = (TextView) activity.findViewById(R.id.sample_text);
        assertNotNull("TextView could not be found", tvHelloWorld);
        assertTrue("TextView contains incorrect text",
                "Test".equals(tvHelloWorld.getText().toString()));

    }

    @Test
    public void validateOnClickToStartXApiLoginClass() {

        Button button = (Button) activity.findViewById( R.id.buttonXApi );
        button.performClick();
        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(xApiLogin.class.getCanonicalName(), intent.getComponent().getClassName());

    }

}



