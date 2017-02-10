import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.hortensie.ai_trader.xAPI.MainApiActivity;
import com.hortensie.ai_trader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PeriodSelectorScrollViewAndroidTest {

    @Rule
    public ActivityTestRule<MainApiActivity> mActivityTestRule = new ActivityTestRule<>(MainApiActivity.class);

    @Test
    public void periodSelectorScrollViewAndroidTest() {
        ViewInteraction button3 = onView(
                allOf(ViewMatchers.withId(R.id.buttonPeriodSelector), withText("Period selector"),
                        withParent(allOf(withId(R.id.activity_main_live),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.PERIOD_M1), withText("PERIOD_M1"),
                        withParent(withId(R.id.period_selector))));
        radioButton.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton2 = onView(
                allOf(withId(R.id.PERIOD_M5), withText("PERIOD_M5"),
                        withParent(withId(R.id.period_selector))));
        radioButton2.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton3 = onView(
                allOf(withId(R.id.PERIOD_M15), withText("PERIOD_M15"),
                        withParent(withId(R.id.period_selector))));
        radioButton3.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton4 = onView(
                allOf(withId(R.id.PERIOD_M30), withText("PERIOD_M30"),
                        withParent(withId(R.id.period_selector))));
        radioButton4.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton5 = onView(
                allOf(withId(R.id.PERIOD_H1), withText("PERIOD_H1"),
                        withParent(withId(R.id.period_selector))));
        radioButton5.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton6 = onView(
                allOf(withId(R.id.PERIOD_H4), withText("PERIOD_H4"),
                        withParent(withId(R.id.period_selector))));
        radioButton6.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton7 = onView(
                allOf(withId(R.id.PERIOD_D1), withText("PERIOD_D1"),
                        withParent(withId(R.id.period_selector))));
        radioButton7.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton8 = onView(
                allOf(withId(R.id.PERIOD_W1), withText("PERIOD_W1"),
                        withParent(withId(R.id.period_selector))));
        radioButton8.perform(scrollTo(), click());

        pressBack();

        ViewInteraction radioButton9 = onView(
                allOf(withId(R.id.PERIOD_MN1), withText("PERIOD_MN1"),
                        withParent(withId(R.id.period_selector))));
        radioButton9.perform(scrollTo(), click());

    }

}
