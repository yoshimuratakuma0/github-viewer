package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.core.Result
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GithubUserDetailViewModel @AssistedInject constructor(
    @Assisted private val username: String,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(username: String): GithubUserDetailViewModel
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState get() : StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getUserDetailUseCase.execute(GetUserDetailInputParams(username)).let { result ->
                when (result) {
                    is Result.Error -> {
                        _uiState.value = UiState.ErrorState(result.exception)
                    }
                    is Result.Success -> {
                        _uiState.value = UiState.Success(result.data)
                    }
                }
            }
        }
    }

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        class Success(val userDetail: UserDetail) : UiState()
        class ErrorState(val exception: Exception) : UiState()
    }
}