package com.free.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.KEY_USERNAME
import com.free.domain.entities.User
import com.free.domain.usecases.FetchFollowersInputParams
import com.free.domain.usecases.FetchFollowersUseCase
import com.free.domain.usecases.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface GitHubFollowersUiState {
    data object Success : GitHubFollowersUiState
    data class Error(val exception: Exception) : GitHubFollowersUiState
    data object Loading : GitHubFollowersUiState
}

class FollowersListingData(
    val children: List<User>,
    val params: FetchFollowersInputParams,
)

@HiltViewModel
class GitHubFollowersViewModel @Inject constructor(
    private val fetchFollowersUseCase: FetchFollowersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GitHubFollowersUiState>(GitHubFollowersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    private val _listing = MutableStateFlow<FollowersListingData?>(null)
    val listing = _listing.asStateFlow()

    init {
        fetchMore()
    }

    fun fetchMore() = synchronized(this) {
        viewModelScope.launch {
            _listing.update { currentListing ->
                val nextParams = if (currentListing == null) {
                    // Initial fetch
                    FetchFollowersInputParams(
                        username = username,
                        perPage = 50,
                        since = null,
                    )
                } else {
                    // Fetch more
                    val nextParams = currentListing.params.copy(
                        since = currentListing.children.lastOrNull()?.id
                    )
                    // Don't send the same request
                    if (nextParams == currentListing.params) {
                        return@update currentListing
                    }
                    nextParams
                }

                when (val result = fetchFollowersUseCase(nextParams)) {
                    is Result.Error -> {
                        _uiState.value = GitHubFollowersUiState.Error(result.exception)
                        currentListing
                    }

                    is Result.Success -> {
                        _uiState.value = GitHubFollowersUiState.Success

                        // Don't add the same list.
                        // since param doesn't seem to work well.
                        // So, sometimes we get the same list even if since param is different
                        if (currentListing?.children?.lastOrNull() == result.data.lastOrNull()) {
                            return@update currentListing
                        }
                        FollowersListingData(
                            (currentListing?.children ?: emptyList()) + result.data,
                            nextParams,
                        )
                    }
                }
            }
        }
    }
}