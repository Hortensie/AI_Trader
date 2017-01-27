import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.vaadin.polymer.demo.client.sampler.ai_trader.MainOffline;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTraderAndroidTest {

    @Rule
    public ActivityTestRule<MainOffline> mActivityTestRule = new ActivityTestRule<>(MainOffline.class);

    @Test
    public void mainTraderAndroidTest() {

        ViewInteraction button3 = onView(
                allOf(ViewMatchers.withId(R.id.buttonPeriodSelector), withText("Period selector"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.PERIOD_H1), withText("PERIOD_H1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.buttonPickStartDate), withText("Set Start Date"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button5.perform(scrollTo(), click());

        ViewInteraction button6 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button6.perform(scrollTo(), click());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.buttonPickEndDate), withText("Set End Date"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button7.perform(click());

        ViewInteraction button8 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel")));
        button8.perform(scrollTo(), click());

        ViewInteraction button9 = onView(
                allOf(withId(R.id.buttonSetSymbol), withText("Set Symbol"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button9.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.my_recycler_view),
                        withParent(allOf(withId(R.id.activity_trading_symbol),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction button10 = onView(
                allOf(withId(R.id.buttonHistData), withText("Get Historical Data"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button10.perform(click());

        ViewInteraction button11 = onView(
                allOf(withId(R.id.drawCandleChart), withText("Draw Chart"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button11.perform(click());
    }

}
