package com.free.feature_user.items

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.free.design_system.annotations.NightModePreviewAnnotation
import com.free.design_system.components.Card
import com.free.design_system.components.Text
import com.free.design_system.components.TextButton
import com.free.design_system.design_token.MyTheme
import com.free.feature_core.R
import com.free.feature_core.components.AsyncRoundedImage

@Composable
fun UserItem(
    name: String,
    iconUrl: String,
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
                    .size(92.dp)
                    .aspectRatio(1f),
                url = iconUrl,
                placeholderPainter = painterResource(id = R.drawable.ic_account_circle),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = name,
                    style = MyTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.weight(1f))

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
fun PreviewUserItem() {
    UserItem(
        name = "iey",
        iconUrl = "https://avatars.githubusercontent.com/u/6?v=4",
        onClick = {},
        onFollowing = {},
        onFollowers = {},
    )
}