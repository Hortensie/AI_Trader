/**
 * Created by Piotr on 2017-01-02.
 * Expresso UI test to valide if password editText can be used to add new value
 */


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiLogin;

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
