package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.free.domain.entities.User
import com.free.presentation.R
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.items.GithubUserItem

@Composable
fun GithubUsersScreen(navController: NavController, viewModel: GithubUsersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.title_github_users_screen)) }
            )
        },
        content = {
            GithubUserList(viewModel.usersFlow.collectAsLazyPagingItems()) { username ->
                navController.navigate("github_user_detail_screen/${username}")
            }
        }
    )
}

@Composable
fun GithubUserList(lazyPagingItems: LazyPagingItems<User>, onClick: ((username: String) -> Unit)) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(lazyPagingItems) { user ->
            user?.let {
                GithubUserItem(
                    user = it,
                    iconRadius = 40,
                    onClick = onClick
                )
            }
        }
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                }
                loadState.append is LoadState.Error -> {
                }
            }
        }
    }
}