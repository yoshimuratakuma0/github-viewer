package com.free.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.domain.entities.UserDetail
import com.free.presentation.R
import com.free.presentation.viewmodels.GithubUserDetailViewModel

@Composable
fun GithubUserDetailScreen(viewModel: GithubUserDetailViewModel) {
    val iconRadius = 64
    val userDetail: UserDetail by viewModel.userDetail.observeAsState(
        UserDetail.empty()
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = userDetail.avatarUrl)
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
                modifier = Modifier.size((iconRadius * 2).dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.about_followers).format(userDetail.followers),
                    color = colorResource(id = R.color.default_font_color)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.about_following).format(userDetail.following),
                    color = colorResource(id = R.color.default_font_color)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column {
            Text(
                text = stringResource(id = R.string.about_username).format(userDetail.displayName),
                color = colorResource(id = R.color.default_font_color)
            )
            if (userDetail.hasEmail) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.about_email).format(userDetail.email),
                    color = colorResource(id = R.color.default_font_color)
                )
            }
            if (userDetail.hasCompany) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.about_company).format(userDetail.company),
                    color = colorResource(id = R.color.default_font_color)
                )
            }
            if (userDetail.hasBio) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.about_bio).format(userDetail.bio),
                    color = colorResource(id = R.color.default_font_color)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_updated_at).format(
                    userDetail.updatedAt.year,
                    userDetail.updatedAt.monthValue,
                    userDetail.updatedAt.dayOfMonth
                ),
                color = colorResource(id = R.color.default_font_color)
            )
            Text(
                text = stringResource(id = R.string.about_created_at).format(
                    userDetail.createdAt.year,
                    userDetail.createdAt.monthValue,
                    userDetail.createdAt.dayOfMonth
                ),
                color = colorResource(id = R.color.default_font_color)
            )
        }
    }
}