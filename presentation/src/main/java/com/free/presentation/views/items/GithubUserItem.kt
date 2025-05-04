package com.free.presentation.views.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.design_system.annotations.NightModePreviewAnnotation
import com.design_system.components.AsyncRoundedImage
import com.design_system.components.Card
import com.design_system.components.Text
import com.design_system.components.TextButton
import com.design_system.design_token.MyTheme
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
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Min),
        ) {
            AsyncRoundedImage(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f),
                url = user.avatarUrl,
                placeholderPainter = painterResource(id = R.drawable.ic_account_circle),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.height(IntrinsicSize.Min),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = user.username,
                    style = MyTheme.typography.titleMedium,
                )

                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
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