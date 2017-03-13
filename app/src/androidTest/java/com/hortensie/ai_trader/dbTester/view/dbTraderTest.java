package com.hortensie.ai_trader.dbTester.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.hortensie.ai_trader.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class dbTraderTest {

    @Rule
    public ActivityTestRule<MainDbTester> mActivityTestRule = new ActivityTestRule<>(MainDbTester.class);

    @Test
    public void dbTraderTest() {

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Tile"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.viewpager),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(R.id.drawer)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());


        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Symbols"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.viewpager),
                        withParent(allOf(withId(R.id.main_content),
                                withParent(withId(R.id.drawer)))),
                        isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.my_recycler_view),
                        withParent(allOf(withId(R.id.viewpager),
                                withParent(withId(R.id.main_content)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));




    }

}
