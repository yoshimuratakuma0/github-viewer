package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.free.domain.entities.User
import com.free.domain.exceptions.FetchUsersException
import com.free.githubviewer.R
import com.free.presentation.utils.OkAlertDialog
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.items.GithubUserItem
import java.net.UnknownHostException

@Composable
fun GithubUsersScreen(
    viewModel: GithubUsersViewModel,
    onClickUser: (username: String) -> Unit,
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
                GithubUsersScreen(
                    lazyPagingItems = viewModel.usersFlow.collectAsLazyPagingItems(),
                    onClick = onClickUser,
                )
            }
        }
    )
}

@Composable
private fun GithubUsersScreen(
    lazyPagingItems: LazyPagingItems<User>,
    onClick: ((username: String) -> Unit)
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(lazyPagingItems.itemCount) { index ->
            lazyPagingItems[index]?.let { user ->
                GithubUserItem(
                    user = user,
                    iconRadius = 40,
                    onClick = onClick
                )
            }
        }
        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    val error = (loadState.refresh as LoadState.Error).error
                    val titleResId = when (error) {
                        is FetchUsersException.Forbidden -> R.string.error_title_exceed_api_limit
                        is UnknownHostException -> R.string.error_title_network_error
                        else -> R.string.error_title_unexpected
                    }
                    val bodyResId = when (error) {
                        is FetchUsersException.Forbidden -> R.string.error_exceed_api_limit
                        is UnknownHostException -> R.string.error_network_error
                        else -> R.string.error_unexpected
                    }
                    item {
                        OkAlertDialog(titleResId = titleResId, bodyResId = bodyResId)
                    }
                }
            }
        }
    }
}