package com.free.presentation.views

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.free.domain.entities.User
import com.free.presentation.viewmodels.GitHubUsersUiState
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class GitHubUsersScreenScreenshotTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            GithubUsersStatelessScreen(
                listState = rememberLazyListState(),
                uiState = GitHubUsersUiState.Success,
                users = listOf(
                    User(
                        id = 1,
                        username = "preview name",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                    ),
                    User(
                        id = 2,
                        username = "preview name 2",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                    ),
                    User(
                        id = 3,
                        username = "preview name 3",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                    ),
                ),
                fetchMore = {},
                onClick = {},
                onFollowing = {},
                onFollowers = {},
            )
        }

        composeRule
            .onRoot()
            .captureRoboImage("build/screenshots/github_users_screen.png")
    }
}