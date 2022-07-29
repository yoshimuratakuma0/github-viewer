package com.free.presentation

import androidx.paging.PagingSource
import com.free.domain.Result
import com.free.domain.entities.ListingData
import com.free.domain.entities.User
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import com.free.presentation.views.GithubUsersPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class GithubUsersPagingSourceTest {

    @Test
    fun pagingFailure() = runTest {
        val mockUseCase = mockk<FetchUsersUseCase> {
            coEvery { execute(any()) } returns Result.Error(UnknownHostException())
        }
        val pagingSource = GithubUsersPagingSource(mockUseCase)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(0, 100, true)
        )
        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assert(result.throwable is UnknownHostException)
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
            coEvery { execute(any()) } returns Result.Success(
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