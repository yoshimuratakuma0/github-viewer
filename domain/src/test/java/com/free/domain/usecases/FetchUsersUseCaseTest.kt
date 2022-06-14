package com.free.domain.usecases

import com.free.core.Result
import com.free.core.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@ExperimentalCoroutinesApi
class FetchUsersUseCaseTest {

    @Test
    fun inputParamsSuccess() = runTest {
        val repository = mockk<UsersRepository>()
        coEvery { repository.users(any()) } returns Result.Success(mockk())

        val useCase = FetchUsersUseCase(repository)
        val result = useCase.execute(FetchUsersInputParams(since = null, perPage = 100))
        assert(result is Result.Success)
    }

    @Test
    fun inputParamsFailure() = runTest {
        val repository = mockk<UsersRepository>()
        coEvery { repository.users(any()) } returns Result.Success(mockk())

        val useCase = FetchUsersUseCase(repository)
        val result = useCase.execute(FetchUsersInputParams(since = null, perPage = 101))
        assert(result is Result.Error && result.exception is FetchUsersException)
    }
}