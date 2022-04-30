package com.free.domain.usecases

import com.free.core.Result
import com.free.domain.entities.User
import com.free.domain.repositories.UsersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchUsersUseCaseTest {

    private val mockSuccessRepository = mockk<UsersRepository> {
        coEvery { users(any()) } returns Result.Success(
            listOf(
                User(
                    username = "testname1",
                    avatarUrl = "https://avatar1.com",
                    followersUrl = "https://followers1.com",
                    followingUrl = "https://following1.com",
                    organizationsUrl = "https://organizations1.com"
                ),
                User(
                    username = "testname2",
                    avatarUrl = "https://avatar2.com",
                    followersUrl = "https://followers2.com",
                    followingUrl = "https://following2.com",
                    organizationsUrl = "https://organizations2.com"
                ),
            )
        )
    }

    private val successUseCase = FetchUsersUseCase(mockSuccessRepository)

    @Test
    fun usersSuccessTest() = runTest {
        successUseCase.execute(FetchUsersInputParams(0, 100)).let { result ->
            when (result) {
                is Result.Error -> {
                    assert(false)
                }
                is Result.Success -> {
                    assertEquals("testname1", result.data[0].username)
                }
            }

        }
    }
}