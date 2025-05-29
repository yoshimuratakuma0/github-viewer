package com.free.feature_user.viewmodels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.free.design_system.annotations.NightModePreviewAnnotation
import com.free.design_system.combinePadding
import com.free.design_system.components.CircularProgressIndicator
import com.free.design_system.components.ScaffoldHidingTopAppBar
import com.free.design_system.components.Text
import com.free.design_system.design_token.MyTheme
import com.free.feature_core.R
import com.free.feature_core.components.ErrorAlertDialog
import com.free.feature_user.items.UserList
import com.free.feature_user.models.UserUiModel
import com.free.feature_user.previews.UsersPreviewParameterProvider

@Composable
internal fun UsersStatelessScreen(
    listState: LazyListState,
    uiState: UsersUiState,
    users: List<UserUiModel>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
) {
    ScaffoldHidingTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.title_github_users_screen))
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
                padding = padding
            )
        }
    )
}

@Composable
fun UsersContent(
    listState: LazyListState,
    uiState: UsersUiState,
    users: List<UserUiModel>,
    fetchMore: () -> Unit,
    onClick: ((username: String) -> Unit),
    onFollowing: (username: String) -> Unit,
    onFollowers: (username: String) -> Unit,
    padding: PaddingValues,
) {
    when (uiState) {
        is UsersUiState.Success -> {
            if (!listState.canScrollForward) {
                LaunchedEffect(users.size) {
                    fetchMore()
                }
            }

            UserList(
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

        is UsersUiState.Error -> {
            ErrorAlertDialog(exception = uiState.exception)
        }

        UsersUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        UsersUiState.NoData -> {
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
fun UsersStatelessScreenPreview(
    @PreviewParameter(UsersPreviewParameterProvider::class)
    uiState: UsersUiState,
) {
    MyTheme {
        UsersStatelessScreen(
            listState = rememberLazyListState(),
            uiState = uiState,
            users = listOf(
                UserUiModel(
                    id = 1,
                    username = "preview name",
                    avatarUrl = "https://localhost/u/1?v=4"
                ),
                UserUiModel(
                    id = 2,
                    username = "preview name 2",
                    avatarUrl = "https://localhost/u/1?v=4"
                ),
                UserUiModel(
                    id = 3,
                    username = "preview name 3",
                    avatarUrl = "https://localhost/u/1?v=4"
                ),
            ),
            fetchMore = { /*TODO*/ },
            onClick = {},
            onFollowing = {},
            onFollowers = {},
        )
    }
}
