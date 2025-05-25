package com.free.feature_follow.screens

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.free.design_system.components.Icon
import com.free.design_system.components.IconButton
import com.free.design_system.components.Icons
import com.free.design_system.components.ScaffoldHidingTopAppBar
import com.free.design_system.components.Text
import com.free.feature_core.R
import com.free.feature_follow.viewmodels.GitHubFollowersViewModel
import com.free.feature_user.models.UserUiModel
import com.free.feature_user.viewmodels.UsersContent
import com.free.feature_user.viewmodels.UsersUiState

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
fun GitHubFollowersScreenStatelessScreen(
    listState: LazyListState,
    uiState: UsersUiState,
    users: List<UserUiModel>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    onBackPressed: () -> Unit,
) {
    ScaffoldHidingTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.followers))
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        content = { padding ->
            UsersContent(
                listState = listState,
                uiState = uiState,
                users = users,
                fetchMore = fetchMore,
                onClick = onClick,
                onFollowing = onFollowing,
                onFollowers = onFollowers,
                padding = padding,
            )
        }
    )
}