package com.example.android.kade_sub_2

import android.content.Context
import android.view.KeyEvent
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchView(){
        Thread.sleep(4000)
        onView(withId(R.id.rv_main))
            .check(matches(isDisplayed()))
        onView(withText("English Premier League")).perform(click())
        Thread.sleep(6000)
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext<Context>())
        onView(withText("Search Match")).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Arsenal"))
        Thread.sleep(3000)
        onView(withId(R.id.search)).perform(
            pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.search)).perform(closeSoftKeyboard())
        Thread.sleep(6000)
        onView(isAssignableFrom(EditText::class.java)).perform(clearText(), typeText("Madrid"))
        Thread.sleep(3000)
        onView(withId(R.id.search)).perform(
            pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.search)).perform(
            ViewActions.closeSoftKeyboard())
        Thread.sleep(4000)
    }
}