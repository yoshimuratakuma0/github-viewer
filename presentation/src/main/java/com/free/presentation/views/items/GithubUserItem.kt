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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.design_system.annotations.NightModePreviewAnnotation
import com.design_system.components.AsyncRoundedImage
import com.design_system.components.Card
import com.design_system.components.Text
import com.design_system.components.TextButton
import com.free.domain.entities.User
import com.free.githubviewer.R


@Composable
fun GithubUserItem(
    user: User,
    onClick: () -> Unit,
    onFollowing: () -> Unit,
    onFollowers: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                onClick()
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
                    AsyncRoundedImage(
                        size = iconRadius.intValue.dp,
                        url = user.avatarUrl,
                        placeholderPainter = painterResource(id = R.drawable.ic_account_circle),
                    )
                },
            )

            Column {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.about_id).format(user.id),
                )

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.about_username).format(user.username),
                )

                Row {
                    TextButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.following),
                            )
                        },
                        onClick = onFollowing,
                    )
                    TextButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.followers),
                            )
                        },
                        onClick = onFollowers,
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
    GithubUserItem(user = user, onClick = {}, onFollowing = {}, onFollowers = {})
}