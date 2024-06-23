package com.free.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.free.domain.entities.User
import com.free.githubviewer.R
import com.free.presentation.GitHubUsersPreviewParameterProvider
import com.free.presentation.previews.NightModePreviewAnnotation
import com.free.presentation.utils.OkAlertDialog
import com.free.presentation.utils.errorBodyBy
import com.free.presentation.utils.errorTitleBy
import com.free.presentation.viewmodels.GitHubUsersUiState
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.items.GitHubUserList
import com.free.presentation.views.theme.GithubViewerTheme

@Composable
fun GitHubUsersScreen(
    viewModel: GithubUsersViewModel,
    onClickUser: (username: String) -> Unit,
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val listing by viewModel.listing.collectAsState()
    val listState = rememberLazyListState()

    GithubUsersStatelessScreen(
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
private fun GithubUsersStatelessScreen(
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
        content = {
            Box(modifier = Modifier.padding(it)) {
                when (uiState) {
                    is GitHubUsersUiState.Success -> {
                        if (!listState.canScrollForward) {
                            LaunchedEffect(key1 = users.size) {
                                fetchMore()
                            }
                        }

                        GitHubUserList(
                            listState = listState,
                            users = users,
                            onClick = onClick,
                            onFollowers = onFollowers,
                            onFollowing = onFollowing,
                        )
                    }

                    is GitHubUsersUiState.Error -> {
                        val title = errorTitleBy(exception = uiState.exception)
                        val body = errorBodyBy(exception = uiState.exception)
                        OkAlertDialog(title = title, body = body)
                    }

                    GitHubUsersUiState.Loading -> {
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

@NightModePreviewAnnotation
@Composable
private fun GitHubUsersStatelessScreenPreview(
    @PreviewParameter(GitHubUsersPreviewParameterProvider::class)
    uiState: GitHubUsersUiState,
) {
    GithubViewerTheme {
        GithubUsersStatelessScreen(
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
