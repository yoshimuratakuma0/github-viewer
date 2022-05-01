package com.free.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.colorResource
import com.free.presentation.R
import com.free.presentation.viewmodels.GithubUserDetailViewModel

@Composable
fun GithubUserDetailScreen(viewModel: GithubUserDetailViewModel) {
    val name: String by viewModel.name.observeAsState("")
    Column {
        Text(
            text = name,
            color = colorResource(id = R.color.default_font_color)
        )
    }
}