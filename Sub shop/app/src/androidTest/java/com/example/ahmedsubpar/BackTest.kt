package com.example.ahmedsubpar

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BackTest {

    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    /**
     * this function will test if the back button is working correctly
     * when we are in the receipt fragment and press the back button we should go back to welcome fragment not the order fragment
     */
    @Test
    fun testBack() {

        onView(withId(R.id.welcomeConstraint)).check(matches(isDisplayed()))
        onView(withId(R.id.order_button)).perform(click())
        onView(withId(R.id.order_button)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.welcomeConstraint)).check(matches(isDisplayed()))

    }
}