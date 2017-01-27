import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.vaadin.polymer.demo.client.sampler.ai_trader.MainActivity;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityAiTraderAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityAiTraderAndroidTest() {
        ViewInteraction linearLayout = onView(
                allOf(ViewMatchers.withId(R.id.activity_main),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.historicalData), withText("AI Tester"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.content),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonXApi), withText("Login to xAPI"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.activity_x_api_logi),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.buttonLogin), withText("Login"),
                        withParent(allOf(withId(R.id.activity_x_api_logi),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.activity_main_live),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.buttonGetSymbols), withText("Get Symbols from API"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.buttonGetSymbolsApi), withText("Get symbols from db"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.buttonPeriodSelector), withText("Period selector"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction radioGroup = onView(
                allOf(withId(R.id.period_selector),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        radioGroup.check(matches(isDisplayed()));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.PERIOD_M1), withText("PERIOD_M1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton.perform(click());

        pressBack();

        ViewInteraction radioButton2 = onView(
                allOf(withId(R.id.PERIOD_M5), withText("PERIOD_M5"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton2.perform(click());

        pressBack();

        ViewInteraction radioButton3 = onView(
                allOf(withId(R.id.PERIOD_M15), withText("PERIOD_M15"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton3.perform(click());

        pressBack();

        ViewInteraction radioButton4 = onView(
                allOf(withId(R.id.PERIOD_M30), withText("PERIOD_M30"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton4.perform(click());

        pressBack();

        ViewInteraction radioButton5 = onView(
                allOf(withId(R.id.PERIOD_H1), withText("PERIOD_H1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton5.perform(click());

        pressBack();

        ViewInteraction radioButton6 = onView(
                allOf(withId(R.id.PERIOD_H4), withText("PERIOD_H4"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton6.perform(click());

        pressBack();

        ViewInteraction radioButton7 = onView(
                allOf(withId(R.id.PERIOD_W1), withText("PERIOD_W1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton7.perform(click());

        pressBack();

        ViewInteraction radioButton8 = onView(
                allOf(withId(R.id.PERIOD_MN1), withText("PERIOD_MN1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton8.perform(click());

        pressBack();

        ViewInteraction radioButton9 = onView(
                allOf(withId(R.id.PERIOD_D1), withText("PERIOD_D1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton9.perform(click());

        ViewInteraction linearLayout5 = onView(
                allOf(withId(R.id.activity_x_api_trading_input),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        linearLayout5.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.buttonPickStartDate), withText("Set Start Date"),
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
                allOf(withId(R.id.buttonPickEndDate), withText("Set End Date"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button10.perform(click());

        ViewInteraction button11 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button11.perform(scrollTo(), click());

        ViewInteraction button12 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        button12.perform(scrollTo(), click());

        ViewInteraction button13 = onView(
                allOf(withId(R.id.buttonSetSymbol), withText("Set Symbol"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button13.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.my_recycler_view),
                        withParent(allOf(withId(R.id.activity_trading_symbol),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction button14 = onView(
                allOf(withId(R.id.buttonHistData), withText("Get Historical Data"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button14.perform(click());

        ViewInteraction button15 = onView(
                allOf(withId(R.id.drawCandleChart), withText("Draw Chart"),
                        withParent(allOf(withId(R.id.activity_x_api_trading_input),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button15.perform(click());

        ViewInteraction linearLayout6 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.content),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout6.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
