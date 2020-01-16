package com.example.nandi.footballclub.main.team

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.nandi.footballclub.R.id.*
import com.example.nandi.footballclub.main.main.MainActivity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun instrumentTeam() {
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(team))
                .perform(click())

        onView(withId(spinner_team))
                .check(matches(isDisplayed()))
        onView(withId(spinner_team))
                .perform(click())
        Thread.sleep(3000)

        onView(withText("Italian Serie A"))
                .perform(click())
        Thread.sleep(3000)
        onView(withId(rc_team))
                .check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(rc_team))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(rc_team))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        onView(withText("PLAYER"))
                .check(matches(isDisplayed()))
        onView(withText("PLAYER"))
                .perform(click())
        Thread.sleep(3000)
        Espresso.pressBack()

        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorites))
                .perform(click())
        onView(withText("TEAM"))
                .check(matches(isDisplayed()))
        onView(withText("TEAM"))
                .perform(click())
        onView(withId(rv_team_favorite))
                .check(matches(isDisplayed()))
        onView(withId(rv_team_favorite))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        Espresso.pressBack()
    }
}