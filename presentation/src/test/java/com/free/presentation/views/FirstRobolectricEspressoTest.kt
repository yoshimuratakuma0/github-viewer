package com.free.presentation.views

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.free.presentation.FakeResponses
import com.github.takahirom.roborazzi.captureRoboImage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
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
@Config(
    application = HiltTestApplication::class,
)
@RunWith(RobolectricTestRunner::class)
class GitHubUsersActivityTest {
    fun loadJSONFromResource(fileName: String): String {
        return this::class.java.classLoader?.getResource(fileName)?.readText()
            ?: throw IllegalArgumentException("File not found: $fileName")
    }

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        hiltRule.inject()

        mockWebServer = MockWebServer()
        mockWebServer.start(FakeUrlModule.PORT)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_user_list() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(FakeResponses.githubUsers)
        )

        Robolectric.buildActivity(MainActivity::class.java)
        launch(MainActivity::class.java)
        onView(isRoot())
            .captureRoboImage()
    }
}


