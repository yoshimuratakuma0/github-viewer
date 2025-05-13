package com.free.presentation.views

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
import com.free.feature_core.components.ErrorAlertDialog
import com.free.feature_user.items.GitHubUserList
import com.free.githubviewer.R
import com.free.presentation.viewmodels.GitHubFollowingUiState
import com.free.presentation.viewmodels.GitHubFollowingViewModel

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
                },
            )
        },
        content = { padding ->
            when (uiState) {
                is GitHubFollowingUiState.Success -> {
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

                is GitHubFollowingUiState.Error -> {
                    ErrorAlertDialog(exception = uiState.exception)
                }

                GitHubFollowingUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                GitHubFollowingUiState.NoData -> {
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