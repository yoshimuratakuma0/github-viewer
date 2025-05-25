package com.free.feature_user.viewmodels

import com.free.feature_user.models.UserDetailUiModel

sealed interface GitHubUserDetailUiState {
    data object Loading : GitHubUserDetailUiState
    data class Error(val exception: Exception) : GitHubUserDetailUiState
    data class Success(val userDetailUiModel: UserDetailUiModel) : GitHubUserDetailUiState
}
