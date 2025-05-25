package com.free.feature_user.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.KEY_USERNAME
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import com.free.domain.usecases.Result
import com.free.feature_user.models.UserDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GitHubUserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    private val _uiState =
        MutableStateFlow<GitHubUserDetailUiState>(GitHubUserDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchUserDetail()
    }

    fun fetchUserDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = GitHubUserDetailUiState.Loading
            when (val result = getUserDetailUseCase(GetUserDetailInputParams(username))) {
                is Result.Error -> {
                    _uiState.value = GitHubUserDetailUiState.Error(result.exception)
                }

                is Result.Success -> {
                    val uiModel = UserDetailUiModel.fromDomain(result.data)
                    _uiState.value = GitHubUserDetailUiState.Success(userDetailUiModel = uiModel)
                }
            }
        }
    }
}