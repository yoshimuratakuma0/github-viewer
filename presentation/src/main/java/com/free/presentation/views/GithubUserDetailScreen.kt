package com.free.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.free.presentation.viewmodels.GithubUserDetailViewModel

@Composable
fun GithubUserDetailScreen(viewModel: GithubUserDetailViewModel) {
    Column {
        Text(text = "screen 2")
    }
}