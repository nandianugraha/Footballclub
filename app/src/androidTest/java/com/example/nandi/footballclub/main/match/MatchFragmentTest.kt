package com.example.nandi.footballclub.main.match

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.nandi.footballclub.main.main.MainActivity
import com.example.nandi.footballclub.R.id.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun instrumentMatch() {
        onView(withId(spinner_match))
                .check(matches(isDisplayed()))
        onView(withId(spinner_match))
                .perform(click())

        onView(withText("Italian Serie A"))
                .perform(click())
        Thread.sleep(3000)
        onView(withId(rc_match))
                .check(matches(isDisplayed()))
        onView(withId(rc_match))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        Espresso.pressBack()

        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorites))
                .perform(click())
        onView(withId(rv_favorites))
                .check(matches(isDisplayed()))
        onView(withId(rv_favorites))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        Espresso.pressBack()


    }
}