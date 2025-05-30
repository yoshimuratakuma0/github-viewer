package com.free.feature_user.screens

import com.free.domain.usecases.FetchUsersUseCase
import com.free.feature_user.viewmodels.GitHubUsersViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GitHubUsersViewModelPagingTest {
    @Test
    fun fetchUsers_when_success() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val viewModel = GitHubUsersViewModel(
            ioDispatcher = dispatcher,
            fetchUsersUseCase = FetchUsersUseCase(
                repository = FakeUsersRepositoryForPaging(),
                ioDispatcher = dispatcher,
            )
        )

        val jobs = List<Job>(3) {
            async { viewModel.fetchMore() }
        }
        jobs.forEach { it.join() }

        testScheduler.advanceUntilIdle()

        assertEquals(200, viewModel.listing.value!!.children.size)

        assertEquals(50, viewModel.listing.value!!.children[49].id)
        assertEquals(100, viewModel.listing.value!!.children[99].id)
        assertEquals(150, viewModel.listing.value!!.children[149].id)
        assertEquals(200, viewModel.listing.value!!.children[199].id)
    }
}