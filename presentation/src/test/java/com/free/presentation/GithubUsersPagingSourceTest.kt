package com.free.presentation

import androidx.paging.PagingSource
import com.free.domain.entities.ListingData
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.Result
import com.free.presentation.views.GithubUsersPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubUsersPagingSourceTest {

    @Test
    fun pagingFailure() = runTest {
        val mockUseCase = mockk<FetchUsersUseCase> {
            coEvery { this@mockk.invoke(any()) } returns Result.Error(Exception())
        }
        val pagingSource = GithubUsersPagingSource(mockUseCase)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(0, 100, true)
        )
        assert(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun pagingSuccess() = runTest {
        val users = listOf(
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

        val params = FetchUsersInputParams(
            since = null,
            perPage = 2
        )

        val mockApi = mockk<FetchUsersUseCase> {
            coEvery { this@mockk.invoke(any()) } returns Result.Success(
                ListingData(
                    children = users,
                    params = params
                )
            )
        }

        val pagingSource = GithubUsersPagingSource(mockApi)
        val expected = PagingSource.LoadResult.Page(
            data = users,
            prevKey = null,
            nextKey = 10
        )
        assertEquals(
            expected, pagingSource.load(
                PagingSource.LoadParams.Refresh(0, params.perPage, false)
            )
        )
    }
}