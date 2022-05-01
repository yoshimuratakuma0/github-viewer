package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.free.domain.entities.User
import com.free.presentation.viewmodels.GithubUsersViewModel
import com.free.presentation.views.items.GithubUserItem

@Composable
fun GithubUsersScreen(navController: NavController, viewModel: GithubUsersViewModel) {
    val users: List<User> by viewModel.users.observeAsState(emptyList())
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(users) { user ->
            GithubUserItem(
                user = user,
                iconRadius = 40,
                onClick = { username: String ->
                    navController.navigate("github_user_detail_screen/${username}")
                }
            )
        }
    }
}