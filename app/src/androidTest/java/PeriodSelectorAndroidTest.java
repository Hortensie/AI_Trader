import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.vaadin.polymer.demo.client.sampler.ai_trader.PeriodSelector;
import com.vaadin.polymer.demo.client.sampler.ai_trader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PeriodSelectorAndroidTest {

    @Rule
    public ActivityTestRule<PeriodSelector> mActivityTestRule = new ActivityTestRule<>(PeriodSelector.class);

    @Test
    public void periodSelectorAndroidTest() {

        ViewInteraction radioButton = onView(
                allOf(ViewMatchers.withId(R.id.PERIOD_M1), withText("PERIOD_M1"),
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
                allOf(withId(R.id.PERIOD_D1), withText("PERIOD_D1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton7.perform(click());

        pressBack();

        ViewInteraction radioButton8 = onView(
                allOf(withId(R.id.PERIOD_W1), withText("PERIOD_W1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton8.perform(click());

        pressBack();

        ViewInteraction radioButton9 = onView(
                allOf(withId(R.id.PERIOD_MN1), withText("PERIOD_MN1"),
                        withParent(allOf(withId(R.id.period_selector),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        radioButton9.perform(click());


    }
}
