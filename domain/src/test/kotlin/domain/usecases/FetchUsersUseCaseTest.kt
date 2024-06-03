package domain.usecases

import com.free.domain.exceptions.FetchUsersException
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class FetchUsersUseCaseTest {

    private lateinit var testDispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
    }

    @Test
    fun inputParamsSuccess() = runTest(testDispatcher) {
        val repository = mockk<UsersRepository>()
        coEvery { repository.users(any()) } returns mockk()

        val useCase = FetchUsersUseCase(repository, testDispatcher)
        val result = useCase(FetchUsersInputParams(since = null, perPage = 100))
        assert(result is Result.Success)
    }

    @Test
    fun inputParamsFailure() = runTest(testDispatcher) {
        val repository = mockk<UsersRepository>()
        coEvery { repository.users(any()) } returns mockk()

        val useCase = FetchUsersUseCase(repository, testDispatcher)
        val result = useCase(FetchUsersInputParams(since = null, perPage = 101))
        assert(result is Result.Error && result.exception is FetchUsersException)
    }
}