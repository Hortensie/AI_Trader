import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.vaadin.polymer.demo.client.sampler.ai_trader.R;
import com.vaadin.polymer.demo.client.sampler.ai_trader.xApiUiInput;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainOfflineCalendarSelectorAndroidTest {

    @Rule
    public ActivityTestRule<xApiUiInput> mActivityTestRule = new ActivityTestRule<>(xApiUiInput.class);

    @Test
    public void mainOfflineCalendarSelectorAndroidTest() {
        ViewInteraction button2 = onView(
                allOf(ViewMatchers.withId(R.id.buttonPickStartDate), withText("Set Start Date"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button3.perform(scrollTo(), click());

        ViewInteraction button4 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button4.perform(scrollTo(), click());

    }

}
