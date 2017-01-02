import android.content.Intent;
import android.util.Log;

import com.vaadin.polymer.demo.client.sampler.ai_trader.MainActivity;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiLogin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.google.android.gms.internal.zzs.TAG;
import static org.assertj.core.api.Java6Assertions.*;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by Piotr on 2017-01-02.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class roboTest {

    @Test
    public void clickingLogin_shouldStartLoginActivity() {

            try {
                System.loadLibrary("native-lib");
                MainActivity activity = Robolectric.setupActivity(MainActivity.class);
                activity.findViewById(R.id.buttonXApi).performClick();

                Intent expectedIntent = new Intent(activity, xApiLogin.class);
                assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);

            } catch (final UnsatisfiedLinkError e) {
                Log.e("error", "loadLibrary" + Log.getStackTraceString(e));
            }


    }

}
