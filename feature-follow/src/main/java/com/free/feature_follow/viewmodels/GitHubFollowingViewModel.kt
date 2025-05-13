package com.free.feature_follow.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.KEY_USERNAME
import com.free.domain.entities.User
import com.free.domain.usecases.FetchFollowingInputParams
import com.free.domain.usecases.FetchFollowingUseCase
import com.free.domain.usecases.Result
import com.free.feature_user.viewmodels.GitHubUsersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class FollowingListingData(
    val children: List<User>,
    val params: FetchFollowingInputParams,
)

@HiltViewModel
class GitHubFollowingViewModel @Inject constructor(
    private val fetchFollowingUseCase: FetchFollowingUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GitHubUsersUiState>(GitHubUsersUiState.Loading)
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
                val nextParams = currentListing?.params?.copy(
                    since = currentListing.children.lastOrNull()?.id
                ) ?: FetchFollowingInputParams(
                    username = username,
                    perPage = 50,
                    since = null,
                )

                when (val result = fetchFollowingUseCase(nextParams)) {
                    is Result.Error -> {
                        _uiState.value = GitHubUsersUiState.Error(result.exception)
                        currentListing
                    }

                    is Result.Success -> {
                        val currentList = currentListing?.children
                        // Initial fetch
                        if (currentList == null) {
                            if (result.data.isEmpty()) {
                                _uiState.value = GitHubUsersUiState.NoData
                            } else {
                                _uiState.value = GitHubUsersUiState.Success
                            }
                            return@update FollowingListingData(result.data, nextParams)
                        }

                        // Don't add the same list.
                        // since param doesn't seem to work well.
                        // So, sometimes we get the same list even if since param is different
                        if (currentList.lastOrNull() == result.data.lastOrNull()) {
                            return@update currentListing
                        }

                        _uiState.value = GitHubUsersUiState.Success
                        FollowingListingData(
                            currentList + result.data,
                            nextParams,
                        )
                    }
                }
            }
        }
    }
}