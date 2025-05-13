package com.free.feature_user.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.domain.entities.User
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
    data object NoData : GitHubUsersUiState
    data object Loading : GitHubUsersUiState
}

class UserListingData(
    val children: List<User>,
    val params: FetchUsersInputParams,
)

@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GitHubUsersUiState>(GitHubUsersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _listing = MutableStateFlow<UserListingData?>(null)
    val listing = _listing.asStateFlow()

    init {
        fetchMore()
    }

    fun fetchMore() = synchronized(this) {
        viewModelScope.launch {
            _listing.update { currentListing ->
                val nextParams = currentListing?.params?.copy(
                    since = currentListing.children.lastOrNull()?.id
                ) ?: FetchUsersInputParams(
                    perPage = 50,
                    since = null,
                )

                when (val result = fetchUsersUseCase(nextParams)) {
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
                            return@update UserListingData(result.data, nextParams)
                        }

                        // Don't add the same list.
                        // since param doesn't seem to work well.
                        // So, sometimes we get the same list even if since param is different
                        if (currentList.lastOrNull() == result.data.lastOrNull()) {
                            return@update currentListing
                        }
                        UserListingData(
                            currentList + result.data,
                            nextParams,
                        )
                    }
                }
            }
        }
    }
}