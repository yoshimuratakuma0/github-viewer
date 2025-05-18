package com.free.feature_user.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.free.design_system.annotations.NightModePreviewAnnotation
import com.free.domain.entities.User

@Composable
fun GitHubUserList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    listState: LazyListState,
    users: List<User>,
    onClick: (String) -> Unit,
    onFollowers: (String) -> Unit,
    onFollowing: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(users) { user ->
            GitHubUserItem(
                user = user,
                onClick = {
                    onClick(user.username)
                },
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

@NightModePreviewAnnotation
@Composable
private fun GitHubUserListPreview() {
    GitHubUserList(
        contentPadding = PaddingValues(),
        listState = LazyListState(),
        users = listOf(
            User(
                id = 1,
                username = "octocat",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            ),
            User(
                id = 2,
                username = "octocatoctocatoctocatoctocatoctocat",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            ),
            User(
                id = 1,
                username = "octocat",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            ),
        ),
        onClick = {},
        onFollowers = {},
        onFollowing = {},
    )
}