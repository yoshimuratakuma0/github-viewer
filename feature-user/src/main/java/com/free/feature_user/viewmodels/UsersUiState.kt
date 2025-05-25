package com.free.feature_user.viewmodels

sealed interface UsersUiState {
    data object Success : UsersUiState
    data class Error(val exception: Exception) : UsersUiState
    data object NoData : UsersUiState
    data object Loading : UsersUiState
}