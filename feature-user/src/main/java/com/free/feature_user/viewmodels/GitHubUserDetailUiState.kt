package com.free.feature_user.viewmodels

import com.free.domain.entities.UserDetail

sealed interface GitHubUserDetailUiState {
    data object Loading : GitHubUserDetailUiState
    data class Error(val exception: Exception) : GitHubUserDetailUiState
    data class Success(val userDetail: UserDetail) : GitHubUserDetailUiState
}
