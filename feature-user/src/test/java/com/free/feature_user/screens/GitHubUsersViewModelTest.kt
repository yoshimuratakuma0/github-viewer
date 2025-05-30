package com.free.feature_user.screens

import com.free.domain.usecases.FetchUsersUseCase
import com.free.feature_user.viewmodels.GitHubUsersViewModel
import com.free.feature_user.viewmodels.UsersUiState
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GitHubUsersViewModelTest {
    @Test
    fun fetchUsers_when_success() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = GitHubUsersViewModel(
            ioDispatcher = dispatcher,
            fetchUsersUseCase = FetchUsersUseCase(
                repository = FakeUsersRepository(),
                ioDispatcher = dispatcher,
            )
        )

        assertEquals(UsersUiState.Loading, viewModel.uiState.value)

        testScheduler.advanceUntilIdle()

        assertEquals(UsersUiState.Success, viewModel.uiState.value)
        assertEquals(3, viewModel.listing.value!!.children.size)
    }

    @Test
    fun fetchUsers_when_no_data() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = GitHubUsersViewModel(
            ioDispatcher = dispatcher,
            fetchUsersUseCase = FetchUsersUseCase(
                repository = FakeUsersRepositoryWithEmptyUsers(),
                ioDispatcher = dispatcher,
            )
        )

        assertEquals(UsersUiState.Loading, viewModel.uiState.value)

        testScheduler.advanceUntilIdle()

        assertEquals(UsersUiState.NoData, viewModel.uiState.value)
        assertTrue(viewModel.listing.value!!.children.isEmpty())
    }

    @Test
    fun fetchUsers_when_error() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = GitHubUsersViewModel(
            ioDispatcher = dispatcher,
            fetchUsersUseCase = FetchUsersUseCase(
                repository = FakeUsersRepositoryWithError(),
                ioDispatcher = dispatcher,
            )
        )

        assertEquals(UsersUiState.Loading, viewModel.uiState.value)

        testScheduler.advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UsersUiState.Error)
    }
}