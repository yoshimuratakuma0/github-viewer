package com.free.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.entities.UserListingData
import com.free.domain.usecases.FetchUsersInputParams
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface GitHubUsersUiState {
    data object Success : GitHubUsersUiState
    data class Error(val exception: Exception) : GitHubUsersUiState
    data object Loading : GitHubUsersUiState
}

@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GitHubUsersUiState>(GitHubUsersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _listing = MutableStateFlow(
        UserListingData(
            children = emptyList(),
            params = FetchUsersInputParams(
                perPage = 50,
                since = null,
            )
        )
    )
    val listing = _listing.asStateFlow()

    init {
        fetchMore()
    }

    fun fetchMore() = synchronized(this) {
        viewModelScope.launch {
            _listing.update { currentListing ->
                val nextParams = listing.value.params.copy(
                    since = listing.value.children.lastOrNull()?.id
                )
                when (val result = fetchUsersUseCase(nextParams)) {
                    is Result.Error -> {
                        _uiState.value = GitHubUsersUiState.Error(result.exception)
                        currentListing
                    }

                    is Result.Success -> {
                        _uiState.value = GitHubUsersUiState.Success
                        UserListingData(
                            currentListing.children + result.data.children,
                            result.data.params,
                        )
                    }
                }
            }
        }
    }
}