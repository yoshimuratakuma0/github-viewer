package com.free.presentation.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.free.domain.entities.User


@Composable
fun GithubUserItem(user: User) {
    Row {
        Image(
            painter = rememberAsyncImagePainter(user.avatarUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(text = user.username, color = Color.Gray)
    }
}