package com.free.presentation.views

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.free.data.di.RepositoryModule
import com.github.takahirom.roborazzi.captureRoboImage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode


@GraphicsMode(GraphicsMode.Mode.NATIVE)
@HiltAndroidTest
@UninstallModules(RepositoryModule::class)
@Config(
    application = HiltTestApplication::class,
)
@RunWith(RobolectricTestRunner::class)
class GitHubUsersActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_user_list() {
        Robolectric.buildActivity(MainActivity::class.java)
        launch(MainActivity::class.java)
        onView(isRoot())
            .captureRoboImage()
    }
}


