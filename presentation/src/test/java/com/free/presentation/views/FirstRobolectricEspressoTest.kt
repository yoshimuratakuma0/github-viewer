package com.free.presentation.views

import android.R
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(
    application = HiltTestApplication::class,
)
@RunWith(RobolectricTestRunner::class)
class FirstRobolectricEspressoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
        val activityScenario = launch(MainActivity::class.java)

        onView(withId(R.id.content))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}


