package com.free.feature_user.screens

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.design_system.annotations.NightModePreviewAnnotation
import com.design_system.combinePadding
import com.design_system.components.CircularProgressIndicator
import com.design_system.components.Scaffold
import com.design_system.components.Text
import com.design_system.components.TopAppBar
import com.design_system.design_token.MyTheme
import com.free.domain.entities.User
import com.free.feature_core.R
import com.free.feature_core.components.ErrorAlertDialog
import com.free.feature_user.items.GitHubUserList
import com.free.feature_user.previews.GitHubUsersPreviewParameterProvider
import com.free.feature_user.viewmodels.GitHubUsersUiState
import com.free.feature_user.viewmodels.GitHubUsersViewModel

@Composable
fun GitHubUsersScreen(
    viewModel: GitHubUsersViewModel,
    onClickUser: (username: String) -> Unit,
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val listing by viewModel.listing.collectAsState()
    val listState = rememberLazyListState()

    GitHubUsersStatelessScreen(
        listState = listState,
        uiState = uiState,
        users = listing?.children ?: emptyList(),
        fetchMore = viewModel::fetchMore,
        onClick = onClickUser,
        onFollowing = onFollowing,
        onFollowers = onFollowers,
    )
}

@Composable
fun GitHubUsersStatelessScreen(
    listState: LazyListState,
    uiState: GitHubUsersUiState,
    users: List<User>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_github_users_screen))
                }
            )
        },
        content = { padding ->
            GitHubUsersContent(
                listState = listState,
                uiState = uiState,
                users = users,
                fetchMore = fetchMore,
                onClick = onClick,
                onFollowing = onFollowing,
                onFollowers = onFollowers,
                padding = padding
            )
        }
    )
}

@Composable
fun GitHubUsersContent(
    listState: LazyListState,
    uiState: GitHubUsersUiState,
    users: List<User>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    padding: PaddingValues,
) {
    when (uiState) {
        is GitHubUsersUiState.Success -> {
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

        is GitHubUsersUiState.Error -> {
            ErrorAlertDialog(exception = uiState.exception)
        }

        GitHubUsersUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        GitHubUsersUiState.NoData -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.no_data))
            }
        }
    }
}

@NightModePreviewAnnotation
@Composable
private fun GitHubUsersStatelessScreenPreview(
    @PreviewParameter(GitHubUsersPreviewParameterProvider::class)
    uiState: GitHubUsersUiState,
) {
    MyTheme {
        GitHubUsersStatelessScreen(
            listState = rememberLazyListState(),
            uiState = uiState,
            users = listOf(
                User(
                    id = 1,
                    username = "preview name",
                    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                ),
                User(
                    id = 2,
                    username = "preview name 2",
                    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                ),
                User(
                    id = 3,
                    username = "preview name 3",
                    avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                ),
            ),
            fetchMore = { /*TODO*/ },
            onClick = {},
            onFollowing = {},
            onFollowers = {},
        )
    }
}
