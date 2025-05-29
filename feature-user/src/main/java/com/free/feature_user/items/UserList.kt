package com.free.feature_user.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.free.feature_user.models.UserUiModel

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    listState: LazyListState,
    users: List<UserUiModel>,
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
            UserItem(
                name = user.username,
                iconUrl = user.avatarUrl,
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
