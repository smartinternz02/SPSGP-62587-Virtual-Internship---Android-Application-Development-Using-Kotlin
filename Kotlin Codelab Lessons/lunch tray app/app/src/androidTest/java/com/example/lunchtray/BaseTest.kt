package com.example.lunchtray

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BaseTest {

    fun fullOrderFlow() {
        launchActivity<MainActivity>()

        onView(withId(R.id.start_order_btn)).perform(click())

        onView(withId(R.id.cauliflower)).perform(click())

        onView(withId(R.id.next_button)).perform(click())

        onView(withId(R.id.salad)).perform(click())

        onView(withId(R.id.next_button)).perform(click())

        onView(withId(R.id.bread)).perform(click())

        onView(withId(R.id.next_button)).perform(click())
    }
}
