package com.free.presentation.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.domain.entities.User


@Composable
fun GithubUserItem(user: User) {
    val r = 32
    Row {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = user.avatarUrl)
                    .apply(block = {
                        transformations(
                            with(LocalDensity.current) {
                                RoundedCornersTransformation(
                                    topLeft = r.dp.toPx(),
                                    topRight = r.dp.toPx(),
                                    bottomLeft = r.dp.toPx(),
                                    bottomRight = r.dp.toPx(),
                                )
                            }
                        )
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier.size((2 * r).dp)
        )
        Text(text = user.username, color = Color.Gray)
    }
}