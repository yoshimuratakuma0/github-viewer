package com.free.feature_user.viewmodels

sealed interface GitHubUsersUiState {
    data object Success : GitHubUsersUiState
    data class Error(val exception: Exception) : GitHubUsersUiState
    data object NoData : GitHubUsersUiState
    data object Loading : GitHubUsersUiState
}