package com.free.presentation.views.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.free.domain.entities.User

@Composable
fun GitHubUserList(
    listState: LazyListState,
    users: List<User>,
    onClick: (String) -> Unit,
    onFollowers: (String) -> Unit,
    onFollowing: (String) -> Unit,
) {
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