package domain.usecases

import com.free.domain.Result
import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
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