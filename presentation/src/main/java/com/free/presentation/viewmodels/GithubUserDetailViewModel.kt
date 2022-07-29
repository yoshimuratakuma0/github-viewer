package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.free.domain.Result
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

data class UserDetailUiState(
    val userDetail: UserDetail? = null,
    val isLoading: Boolean = false,
    val exception: Exception? = null
)

class GithubUserDetailViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(username: String): GithubUserDetailViewModel
    }

    suspend fun userDetail(): Result<UserDetail> =
        getUserDetailUseCase.execute(GetUserDetailInputParams(username))
}