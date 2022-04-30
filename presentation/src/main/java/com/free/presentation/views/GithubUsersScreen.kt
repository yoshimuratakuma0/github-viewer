package com.free.presentation.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.free.domain.entities.User
import com.free.presentation.viewmodels.GithubUsersViewModel

@Composable
fun GithubUsersScreen(viewModel: GithubUsersViewModel) {
    val users: List<User> by viewModel.users.observeAsState(emptyList())
    LazyColumn {
        items(users) { user ->
            Text(text = user.username)
        }
    }
}