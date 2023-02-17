package com.free.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.presentation.R
import com.free.presentation.viewmodels.GithubUserDetailUiState
import com.free.presentation.viewmodels.GithubUserDetailViewModel
import java.time.LocalDateTime

@Composable
fun GithubUserDetailScreen(
    navController: NavController,
    viewModel: GithubUserDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_github_user_detail_screen))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            when (uiState) {
                is GithubUserDetailUiState.Success -> {
                    GithubUserDetail(
                        userDetail = (uiState as GithubUserDetailUiState.Success).userDetail
                    )
                }

                is GithubUserDetailUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    )
}

@Composable
fun GithubUserDetail(userDetail: UserDetail) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ProfileAbstract(userDetail = userDetail)
        Spacer(modifier = Modifier.height(24.dp))
        ProfileDetail(userDetail = userDetail)
    }
}

@Composable
fun ProfileAbstract(userDetail: UserDetail) {
    val iconRadius = 64
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = userDetail.user.avatarUrl)
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
                text = stringResource(id = R.string.about_id).format(userDetail.id),
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_followers).format(userDetail.followers),
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_following).format(userDetail.following),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun ProfileDetail(userDetail: UserDetail) {
    Column {
        Text(
            text = stringResource(id = R.string.about_username).format(userDetail.displayName),
            color = MaterialTheme.colors.onBackground
        )
        if (userDetail.hasEmail) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_email).format(userDetail.email),
                color = MaterialTheme.colors.onBackground
            )
        }
        if (userDetail.hasCompany) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_company).format(userDetail.company),
                color = MaterialTheme.colors.onBackground
            )
        }
        if (userDetail.hasBio) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.about_bio).format(userDetail.bio),
                color = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.about_updated_at).format(
                userDetail.updatedAt.year,
                userDetail.updatedAt.monthValue,
                userDetail.updatedAt.dayOfMonth
            ),
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.about_created_at).format(
                userDetail.createdAt.year,
                userDetail.createdAt.monthValue,
                userDetail.createdAt.dayOfMonth
            ),
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
fun PreviewGithubUserDetail() {
    val userDetail = UserDetail(
        user = User(
            id = 1,
            username = "preview name",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        email = "sample@gmail.com",
        bio = "this is bio. \n\n\n\n\n長いbio\n\n\n\n\n\n\n\n\n長いbio",
        company = "preview company",
        createdAt = LocalDateTime.MIN,
        updatedAt = LocalDateTime.MAX,
        followers = 12345,
        following = 23456,
        name = "preview name"
    )
    GithubUserDetail(userDetail = userDetail)
}