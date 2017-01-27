import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.vaadin.polymer.demo.client.sampler.ai_trader.MainActivity;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
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
public class AIClickerThroughXApiLogin {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clicking() {
        ViewInteraction button = onView(
                allOf(ViewMatchers.withId(R.id.buttonXApi), withText("Login to xAPI"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonLogin), withText("Login"),
                        withParent(allOf(withId(R.id.activity_x_api_logi),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.buttonPeriodSelector), withText("Period selector"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.PERIOD_M1), withText("PERIOD_M1"),
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
                allOf(withId(android.R.id.button1), withText("OK")));
        button8.perform(scrollTo(), click());

        ViewInteraction button9 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button9.perform(scrollTo(), click());

        ViewInteraction button10 = onView(
                allOf(withId(R.id.buttonSetSymbol), withText("Set Symbol"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button10.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.my_recycler_view),
                        withParent(allOf(withId(R.id.activity_trading_symbol),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction button11 = onView(
                allOf(withId(R.id.buttonHistData), withText("Get Historical Data"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button11.perform(click());

        ViewInteraction button12 = onView(
                allOf(withId(R.id.drawCandleChart), withText("Draw Chart"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button12.perform(click());

        pressBack();

    }

}
