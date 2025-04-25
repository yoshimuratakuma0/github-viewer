package com.free.presentation.views

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

@RunWith(RobolectricTestRunner::class)
class FirstRobolectricComposeTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            Grouping(name = "Robolectric")
        }

        composeRule
            .onNode(hasText("Hello Robolectric!"))
            .assertExists()
    }
}

@Composable
fun Grouping(name: String) {
    Text(text = "Hello $name!")
}