package com.free.feature_user.views

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.design_system.design_token.MyTheme
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.feature_user.screens.GithubUserDetailStatelessScreen
import com.free.feature_user.viewmodels.GithubUserDetailUiState
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import java.time.LocalDateTime

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class GitHubUserDetailScreenshotTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test_success() {
        composeRule.setContent {
            MyTheme {
                GithubUserDetailStatelessScreen(
                    uiState = GithubUserDetailUiState.Success(
                        userDetail = UserDetail(
                            user = User(
                                id = 1,
                                username = "preview name",
                                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                            ),
                            email = null,
                            bio = "短いbio",
                            company = null,
                            createdAt = LocalDateTime.MIN,
                            updatedAt = LocalDateTime.MAX,
                            followers = 1,
                            following = 1,
                            name = null,
                        )
                    ),
                    onRetry = {},
                    onBack = {},
                )
            }
        }

        composeRule
            .onRoot()
            .captureRoboImage()
    }

    @Test
    fun test_loading() {
        composeRule.setContent {
            MyTheme {
                GithubUserDetailStatelessScreen(
                    uiState = GithubUserDetailUiState.Loading,
                    onRetry = {},
                    onBack = {},
                )
            }
        }

        composeRule
            .onRoot()
            .captureRoboImage()
    }

    @Test
    fun test_error() {
        composeRule.setContent {
            MyTheme {
                GithubUserDetailStatelessScreen(
                    uiState = GithubUserDetailUiState.Error(Exception()),
                    onRetry = {},
                    onBack = {},
                )
            }
        }

        composeRule
            .onRoot()
            .captureRoboImage()
    }
}