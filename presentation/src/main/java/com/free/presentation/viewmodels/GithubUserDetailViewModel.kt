package com.free.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.entities.UserDetail
import com.free.domain.usecases.GetUserDetailInputParams
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface GithubUserDetailUiState {
    data object Loading : GithubUserDetailUiState
    data class Success(val userDetail: UserDetail) : GithubUserDetailUiState
}

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val KEY_USERNAME = "username"
    }

    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    val uiState: StateFlow<GithubUserDetailUiState> =
        getUserDetailUseCase(GetUserDetailInputParams(username))
            .map(GithubUserDetailUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GithubUserDetailUiState.Loading,
            )
}