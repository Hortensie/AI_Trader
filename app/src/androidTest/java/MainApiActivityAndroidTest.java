import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hortensie.ai_trader.MainApiActivity;
import com.hortensie.ai_trader.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Piotr on 2017-01-27.
 * Espresso test for MainApiActivity.class
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainApiActivityAndroidTest {

    @Rule
    public ActivityTestRule<MainApiActivity> activityTestRule = new ActivityTestRule(MainApiActivity.class);

    @Test
    public void validateTextView(){

        onView(withText("Other functions")).check(matches(isDisplayed()));
        onView(withId(R.id.main_live_view)).check(matches(withText(containsString("Select period and get historical data"))));

    }


}
