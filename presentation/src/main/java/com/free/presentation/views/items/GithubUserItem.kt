package com.free.presentation.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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
fun GithubUserItem(
    user: User,
    iconRadius: Int = 32
) {
    Card(
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = user.avatarUrl)
                        .apply(block = {
                            transformations(
                                with(LocalDensity.current) {
                                    RoundedCornersTransformation(
                                        topLeft = iconRadius.dp.toPx(),
                                        topRight = iconRadius.dp.toPx(),
                                        bottomLeft = iconRadius.dp.toPx(),
                                        bottomRight = iconRadius.dp.toPx(),
                                    )
                                }
                            )
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier.size((2 * iconRadius).dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = user.username,
                color = Color.Gray
            )
        }
    }
}