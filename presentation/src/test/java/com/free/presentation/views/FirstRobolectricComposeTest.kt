package com.free.presentation.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FirstRobolectricComposeTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            Greeting(name = "Robolectric")
        }

        composeRule
            .onNode(hasText("Hello Robolectric!"))
            .assertExists()
    }


    @Test
    fun roborazziTest() {
        composeRule.setContent {
            Greeting(name = "Robolectric")
        }

        composeRule
            .onNode(hasText("Hello Robolectric!"))
            .captureRoboImage()

        composeRule
            .onRoot()
            .captureRoboImage()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}