package com.free.presentation.views

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import com.free.domain.entities.User
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersUseCase
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.theme.GithubViewerTheme
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubUsersScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun failureUsersByNetworkError() {
        val mockRepository = mockk<UsersRepository> {
            coEvery { users() } returns flow {
                val data = PagingData.from(
                    listOf(
                        User(
                            id = 1,
                            username = "test1",
                            avatarUrl = "https://avatar1.com"
                        ),
                        User(
                            id = 10,
                            username = "test2",
                            avatarUrl = "https://avatar2.com"
                        )
                    )
                )
                emit(data)
            }
        }

        val viewModel = GithubUsersViewModel(FetchUsersUseCase(mockRepository))
        composeTestRule.setContent {
            GithubViewerTheme {
                GithubUsersScreen(viewModel = viewModel, navController = mockk())
            }
        }
        composeTestRule
            .onNodeWithText("test1")
            .assertExists()
        composeTestRule
            .onNodeWithText("test2")
            .assertExists()
        composeTestRule
            .onNodeWithText("test3")
            .assertDoesNotExist()
    }
}