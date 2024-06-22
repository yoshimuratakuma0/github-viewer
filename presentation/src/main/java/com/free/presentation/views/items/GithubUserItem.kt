package com.free.presentation.views.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.free.domain.entities.User
import com.free.githubviewer.R
import com.free.presentation.previews.NightModePreviewAnnotation
import com.free.presentation.utils.AsyncRoundedImage


@Composable
fun GithubUserItem(
    user: User,
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
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Min),
        ) {
            val iconRadius = remember {
                mutableIntStateOf(0)
            }
            val density = LocalDensity.current.density
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .aspectRatio(1.0f)
                    .onGloballyPositioned { coordinates ->
                        iconRadius.intValue = (coordinates.size.height / density).toInt()
                    },
                content = {
                    AsyncRoundedImage(iconRadius = iconRadius.intValue / 2, url = user.avatarUrl)
                },
            )

            Column {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "${user.id}: ${user.username}",
                    style = MaterialTheme.typography.h5,
                )
                Row {
                    TextButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.following),
                                style = MaterialTheme.typography.body1,
                            )
                        },
                        onClick = {},
                    )
                    TextButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.followers),
                                style = MaterialTheme.typography.body1,
                            )
                        },
                        onClick = {},
                    )
                }
            }
        }
    }
}


@NightModePreviewAnnotation
@Composable
fun PreviewGithubUserItem() {
    val user = User(
        id = 6,
        avatarUrl = "https://avatars.githubusercontent.com/u/6?v=4",
        username = "ivey"
    )
    GithubUserItem(user = user, onClick = {})
}