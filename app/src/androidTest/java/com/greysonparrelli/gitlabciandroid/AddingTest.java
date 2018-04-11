package com.greysonparrelli.gitlabciandroid;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests the adding flow in the app.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
@RunWith(AndroidJUnit4.class)
public class AddingTest extends TestBase {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Override
    ActivityTestRule getActivityRule() {
        return mActivityRule;
    }

    @Test
    public void addNumbers() throws Exception {
        // Enter numbers to be added
        onView(withId(R.id.num1))
                .perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.num2))
                .perform(typeText("2"), closeSoftKeyboard());

        // Click the calculate button
        onView(withId(R.id.calc_button)).perform(click());

        onView(withId(R.id.sum))
                .check(matches(withText("3")));
    }
}
