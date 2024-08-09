package com.free.presentation.views.items

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.free.domain.entities.User

@Composable
fun GitHubUserList(
    contentPadding: PaddingValues,
    listState: LazyListState,
    users: List<User>,
    onClick: (String) -> Unit,
    onFollowers: (String) -> Unit,
    onFollowing: (String) -> Unit,
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + 4.dp,
            bottom = contentPadding.calculateBottomPadding()
                    + WindowInsets.systemBars.only(WindowInsetsSides.Bottom)
                .asPaddingValues().calculateBottomPadding(),
        ),
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