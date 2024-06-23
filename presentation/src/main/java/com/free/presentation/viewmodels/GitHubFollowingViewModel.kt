package com.free.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.KEY_USERNAME
import com.free.domain.entities.FollowingListingData
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchFollowingUseCase
import com.free.domain.usecases.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface GitHubFollowingUiState {
    data object Success : GitHubFollowingUiState
    data class Error(val exception: Exception) : GitHubFollowingUiState
    data object Loading : GitHubFollowingUiState
}


@HiltViewModel
class GitHubFollowingViewModel @Inject constructor(
    private val fetchFollowingUseCase: FetchFollowingUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GitHubFollowingUiState>(GitHubFollowingUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val username = checkNotNull(savedStateHandle.get<String>(KEY_USERNAME))

    private val _listing = MutableStateFlow<FollowingListingData?>(null)
    val listing = _listing.asStateFlow()

    init {
        fetchMore()
    }

    fun fetchMore() = synchronized(this) {
        viewModelScope.launch {
            _listing.update { currentListing ->
                val nextParams = if (currentListing == null) {
                    // Initial fetch
                    FetchFollowingInputParams(
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

                when (val result = fetchFollowingUseCase(nextParams)) {
                    is Result.Error -> {
                        _uiState.value = GitHubFollowingUiState.Error(result.exception)
                        currentListing
                    }

                    is Result.Success -> {
                        _uiState.value = GitHubFollowingUiState.Success

                        // Don't add the same list.
                        // since param doesn't seem to work well.
                        // So, sometimes we get the same list even if since param is different
                        if (currentListing?.children?.lastOrNull() == result.data.children.lastOrNull()) {
                            return@update currentListing
                        }
                        FollowingListingData(
                            (currentListing?.children ?: emptyList()) + result.data.children,
                            result.data.params,
                        )
                    }
                }
            }
        }
    }
}