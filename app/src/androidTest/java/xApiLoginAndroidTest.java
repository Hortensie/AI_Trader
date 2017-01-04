/**
 * Created by Piotr on 2017-01-02.
 * Expresso UI test to valide if password editText can be used to add new value
 * 0.2 Release fix
 */


import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.view.WindowManager;

import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiLogin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest

public class xApiLoginAndroidTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<xApiLogin> mActivityRule = new ActivityTestRule<>(
            xApiLogin.class);

    @Before
    public void setUp()
    {
        xApiLogin activity = mActivityRule.getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);

    }

    @Before
    public void initValidString() {
        // Specify a valid string.
        mStringToBetyped = "10073026";
    }

    @Test
    public void changeText_sameActivity() {

        // Type text and then press the button.
        onView(withId(R.id.editTextLogin)).perform(clearText());
        onView(withId(R.id.editTextLogin))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        //onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.editTextLogin))
                .check(matches(withText(mStringToBetyped)));
    }

}
