package com.free.presentation.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.domain.entities.User
import com.free.presentation.R

@Composable
fun GithubUserItem(
    user: User,
    iconRadius: Int = 32,
    onClick: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(user.username)
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier.size((2 * iconRadius).dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_account_circle),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
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
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = user.username,
                color = colorResource(id = R.color.default_font_color)
            )
        }
    }
}