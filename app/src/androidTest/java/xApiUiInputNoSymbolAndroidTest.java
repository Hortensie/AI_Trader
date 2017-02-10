import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.xAPI.xApiUiInput;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class xApiUiInputNoSymbolAndroidTest {

    @Rule
    public ActivityTestRule<xApiUiInput> mActivityTestRule = new ActivityTestRule<>(xApiUiInput.class);

    @Test
    public void xApiUiInputAndroidTest() {

        ViewInteraction button2 = onView(
                allOf(ViewMatchers.withId(R.id.buttonSetSymbol), withText("Set Symbol"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        /*
        ViewInteraction button3 = onView(
                allOf(withId(R.id.buttonHistData), withText("Get Historical Data"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.perform(click());
        */
    }

}
