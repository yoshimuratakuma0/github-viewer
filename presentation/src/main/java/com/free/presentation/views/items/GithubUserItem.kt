package com.free.presentation.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.domain.entities.User
import com.free.githubviewer.R

@Composable
fun GithubUserItem(
    user: User,
    iconRadius: Int = 32,
    onClick: (String) -> Unit
) {
    Card(
        backgroundColor = colorResource(id = R.color.card_background),
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
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview
@Composable
fun PreviewGithubUserItem() {
    val user = User(
        id = 6,
        avatarUrl = "https://avatars.githubusercontent.com/u/6?v=4",
        username = "ivey"
    )
    GithubUserItem(user = user, onClick = {})
}