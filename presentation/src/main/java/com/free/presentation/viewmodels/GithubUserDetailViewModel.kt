package com.free.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import com.free.domain.usecases.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface GithubUserDetailUiState {
    data object Loading : GithubUserDetailUiState
    data class Success(val userDetail: UserDetail) : GithubUserDetailUiState
}

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val KEY_USERNAME = "username"
    }

    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    private val _uiState =
        MutableStateFlow<GithubUserDetailUiState>(GithubUserDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUserDetailUseCase(GetUserDetailInputParams(username))) {
                is Result.Error -> {
                    // TODO error handling
                }

                is Result.Success -> {
                    _uiState.value = GithubUserDetailUiState.Success(result.data)
                }
            }
        }
    }
}