package com.example.ahmedsubpar

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * this test is to make sure the order process is working fine
 */
@RunWith(AndroidJUnit4::class)
class OrderTest {

    @get:Rule
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    /**
     * this function is used to test a specific value of the spinner, the radio buttons and the check boxes
     * @param sandwishType the sandich type string
     * @param breadType the id of the radio button of the bread
     * @param toppings an array containing a list of the check boxes ids
     */
    private fun user_can_change_values(sandwishType: String, breadType: Int, toppings: Array<Int>) {

        onView(withId(R.id.order_button)).perform(click())
        //testing the spinner
        onView(withId(R.id.spinner)).perform(click())
        Espresso.onData(
            CoreMatchers.allOf(
                CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                CoreMatchers.`is`(sandwishType)
            )
        ).perform(click())
        onView(withId(R.id.spinner))
            .check(matches(withSpinnerText(containsString(sandwishType))))

        //testing the radio buttons
        onView(withId(breadType))
            .perform(click())

        onView(withId(breadType))
            .check(matches(isChecked()))


        //testing the check boxes
        toppings.forEach {
            onView(it?.let { it1 -> withId(it1) }).perform(click())
            onView(it?.let { it1 -> withId(it1) }).check(matches(isChecked()))
        }
    }

    /**
     * this test will run different scenarios of the full process of ordering a sandwich
     */
    @Test
    fun user_ordering() {

        var sandwishType1 = arrayOf("Philly Sandwich \$8.0","Club Sandwich \$9.25")
        var sandwishType2 = arrayOf("Lobster roll \$10.5","BBQ Sandwich \$7.65")
        var toppings1 = arrayOf(R.id.cheese,R.id.mayo,R.id.onion,R.id.olives)
        var toppings2 = arrayOf(R.id.lettuce,R.id.Tomato,R.id.bacon,R.id.mayo)
        var toppings3 = arrayOf(R.id.mustard,R.id.lettuce,R.id.onion,R.id.bacon)
        var toppings4 = arrayOf(R.id.cheese,R.id.bacon,R.id.Tomato,R.id.olives)
        var price: Float
        var sandwichName: String
        sandwishType1.forEach {

            sandwichName = it.substring(0, it.length - 5)
            if (sandwichName == "Philly Sandwich") {
                price = 10.0f
            } else {
                price= 11.25f
            }

            user_can_change_values(it,R.id.white_bread_radiobutton,toppings1)
            onView(withId(R.id.order_button)).perform(scrollTo()).perform(click())
            onView(withId(R.id.order_description)).check(matches(withText(containsString("$sandwichName on White \n with: \nOlives\nMayo\nCheese\nOnion\nTotal price: \$$price"))))
            onView(withId(R.id.done_button)).perform(click())

            user_can_change_values(it,R.id.whole_wheat_radiobutton,toppings2)
            onView(withId(R.id.order_button)).perform(scrollTo()).perform(click())
            onView(withId(R.id.order_description)).check(matches(withText(containsString("$sandwichName on Whole wheat \n with: \nTomato\nLettuce\nBacon\nMayo\nTotal price: \$$price"))))
            onView(withId(R.id.done_button)).perform(click())

        }
        sandwishType2.forEach {

            sandwichName = it.substring(0, it.length - 5)
            if (sandwichName == "Lobster roll "){
                price = 12.5f
            } else {
                price = 9.65f
            }
            user_can_change_values(it,R.id.white_bread_radiobutton,toppings3)
            onView(withId(R.id.order_button)).perform(scrollTo()).perform(click())
            onView(withId(R.id.order_description)).check(matches(withText(containsString("$sandwichName on White \n with: \nLettuce\nBacon\nMustard\nOnion\nTotal price: \$$price"))))
            onView(withId(R.id.done_button)).perform(click())

            user_can_change_values(it,R.id.whole_wheat_radiobutton,toppings4)
            onView(withId(R.id.order_button)).perform(scrollTo()).perform(click())
            onView(withId(R.id.order_description)).check(matches(withText(containsString("$sandwichName on Whole wheat \n with: \nTomato\nOlives\nBacon\nCheese\nTotal price: \$$price"))))
            onView(withId(R.id.done_button)).perform(click())
        }

    }

}