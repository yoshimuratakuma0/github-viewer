package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.free.domain.entities.User
import com.free.domain.exceptions.FetchUsersException
import com.free.githubviewer.R
import com.free.presentation.utils.OkAlertDialog
import com.free.presentation.viewmodels.GitHubFollowingUiState
import com.free.presentation.viewmodels.GitHubFollowingViewModel
import com.free.presentation.views.items.GithubUserItem
import java.net.UnknownHostException

@Composable
fun GitHubFollowingScreen(
    viewModel: GitHubFollowingViewModel,
    onClickUser: (username: String) -> Unit,
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val listing by viewModel.listing.collectAsState()
    val listState = rememberLazyListState()

    GitHubFollowingScreenStatelessScreen(
        listState = listState,
        uiState = uiState,
        users = listing?.children ?: emptyList(),
        fetchMore = viewModel::fetchMore,
        onClick = onClickUser,
        onFollowing = onFollowing,
        onFollowers = onFollowers,
        onBackPressed = onBackPressed,
    )
}

@Composable
private fun GitHubFollowingScreenStatelessScreen(
    listState: LazyListState,
    uiState: GitHubFollowingUiState,
    users: List<User>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.following))
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                when (uiState) {
                    is GitHubFollowingUiState.Success -> {
                        if (!listState.canScrollForward) {
                            LaunchedEffect(key1 = users.size) {
                                fetchMore()
                            }
                        }

                        LazyColumn(
                            state = listState,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(8.dp),
                        ) {
                            items(users) { user ->
                                GithubUserItem(
                                    user = user,
                                    onClick = onClick,
                                    onFollowing = {
                                        onFollowing(user.username)
                                    },
                                    onFollowers = {
                                        onFollowers(user.username)
                                    },
                                )
                            }
                        }
                    }

                    is GitHubFollowingUiState.Error -> {
                        val titleResId = when (uiState.exception) {
                            is FetchUsersException.Forbidden -> R.string.error_title_exceed_api_limit
                            is UnknownHostException -> R.string.error_title_network_error
                            else -> R.string.error_title_unexpected
                        }
                        val bodyResId = when (uiState.exception) {
                            is FetchUsersException.Forbidden -> R.string.error_exceed_api_limit
                            is UnknownHostException -> R.string.error_network_error
                            else -> R.string.error_unexpected
                        }
                        OkAlertDialog(titleResId = titleResId, bodyResId = bodyResId)
                    }

                    GitHubFollowingUiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    )
}