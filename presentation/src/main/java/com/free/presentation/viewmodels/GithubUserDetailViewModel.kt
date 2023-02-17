package com.free.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.free.domain.Result
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class UserDetailUiState(
    val userDetail: UserDetail? = null,
    val isLoading: Boolean = false,
    val exception: Exception? = null
)

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val KEY_USERNAME = "username"
    }

    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    suspend fun userDetail(): Result<UserDetail> =
        getUserDetailUseCase(GetUserDetailInputParams(username))
}