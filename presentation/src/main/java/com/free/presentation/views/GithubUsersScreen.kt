package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.items.GithubUserItem

@Composable
fun GithubUsersScreen(navController: NavController, viewModel: GithubUsersViewModel) {
    val lazyPagingItems = viewModel.usersFlow.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(lazyPagingItems) { user ->
            user?.let {
                GithubUserItem(
                    user = it,
                    iconRadius = 40,
                    onClick = { username: String ->
                        navController.navigate("github_user_detail_screen/${username}")
                    }
                )
            }
        }
    }
}