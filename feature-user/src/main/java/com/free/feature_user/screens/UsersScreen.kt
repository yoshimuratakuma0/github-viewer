package com.free.feature_user.screens

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.free.feature_user.viewmodels.GitHubUsersViewModel
import com.free.feature_user.viewmodels.UsersStatelessScreen

@Composable
fun UsersScreen(
    viewModel: GitHubUsersViewModel,
    onClickUser: (username: String) -> Unit,
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val listing by viewModel.listing.collectAsState()
    val listState = rememberLazyListState()

    UsersStatelessScreen(
        listState = listState,
        uiState = uiState,
        users = listing?.children ?: emptyList(),
        fetchMore = viewModel::fetchMore,
        onClick = onClickUser,
        onFollowing = onFollowing,
        onFollowers = onFollowers,
    )
}

