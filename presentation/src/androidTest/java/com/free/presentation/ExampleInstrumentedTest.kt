package com.free.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun test() = runTest {
        val users = listOf(
            User(
                id = 1L,
                name = "Test User1"
            ),
            User(
                id = 2L,
                name = "Test User2"
            ),
        )

        val mockUseCase = mockk<GetUsersUseCase> {
            coEvery { this@mockk.invoke() } returns users
        }
        val viewModel = UsersViewModel(mockUseCase)

        assertEquals(users, viewModel.users.value)
    }
}

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    init {
        viewModelScope.launch {
            _users.value = getUsersUseCase()
        }
    }
}

class GetUsersUseCase : suspend () -> List<User> {
    override suspend fun invoke(): List<User> {
        delay(3000)
        throw TimeoutException()
    }
}

class User(
    val id: Long,
    val name: String,
)