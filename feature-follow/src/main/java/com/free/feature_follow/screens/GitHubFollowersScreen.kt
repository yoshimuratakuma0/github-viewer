package com.free.feature_follow.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.design_system.combinePadding
import com.design_system.components.CircularProgressIndicator
import com.design_system.components.Icon
import com.design_system.components.IconButton
import com.design_system.components.Icons
import com.design_system.components.Scaffold
import com.design_system.components.Text
import com.design_system.components.TopAppBar
import com.free.domain.entities.User
import com.free.feature_core.R
import com.free.feature_core.components.ErrorAlertDialog
import com.free.feature_follow.viewmodels.GitHubFollowersUiState
import com.free.feature_follow.viewmodels.GitHubFollowersViewModel
import com.free.feature_user.items.GitHubUserList

@Composable
fun GitHubFollowersScreen(
    viewModel: GitHubFollowersViewModel,
    onClickUser: (username: String) -> Unit,
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    onBackPressed: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val listing by viewModel.listing.collectAsState()
    val listState = rememberLazyListState()

    GitHubFollowersScreenStatelessScreen(
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
private fun GitHubFollowersScreenStatelessScreen(
    listState: LazyListState,
    uiState: GitHubFollowersUiState,
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
                    Text(text = stringResource(id = R.string.followers))
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = { padding ->
            when (uiState) {
                is GitHubFollowersUiState.Success -> {
                    if (!listState.canScrollForward) {
                        fetchMore()
                    }

                    GitHubUserList(
                        modifier = Modifier,
                        contentPadding = combinePadding(
                            padding,
                            PaddingValues(8.dp),
                        ),
                        listState = listState,
                        users = users,
                        onClick = onClick,
                        onFollowers = onFollowers,
                        onFollowing = onFollowing,
                    )
                }

                is GitHubFollowersUiState.Error -> {
                    ErrorAlertDialog(exception = uiState.exception)
                }

                GitHubFollowersUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                GitHubFollowersUiState.NoData -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.no_data))
                    }
                }
            }
        }
    )
}