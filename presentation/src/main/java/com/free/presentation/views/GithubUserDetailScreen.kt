package com.free.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.core.Result
import com.free.core.exceptions.GithubApiException
import com.free.domain.entities.UserDetail
import com.free.presentation.R
import com.free.presentation.utils.OkAlertDialog
import com.free.presentation.viewmodels.GithubUserDetailViewModel
import com.free.presentation.viewmodels.UserDetailUiState
import java.net.UnknownHostException

@Composable
fun GithubUserDetailScreen(
    navController: NavController,
    viewModel: GithubUserDetailViewModel
) {
    val uiState by produceState(initialValue = UserDetailUiState(isLoading = true)) {
        val detail = viewModel.userDetail()
        value = when (detail) {
            is Result.Success -> {
                UserDetailUiState(userDetail = detail.data)
            }
            is Result.Error -> {
                UserDetailUiState(exception = detail.exception)
            }
        }
    }

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
            when {
                uiState.userDetail != null -> {
                    GithubUserDetail(
                        userDetail = uiState.userDetail!!
                    )
                }
                uiState.exception != null -> {
                    val titleResId = when (uiState.exception) {
                        is GithubApiException.ForbiddenException -> R.string.error_title_exceed_api_limit
                        is UnknownHostException -> R.string.error_title_network_error
                        else -> R.string.error_title_unknown
                    }
                    val bodyResId = when (uiState.exception) {
                        is GithubApiException.ForbiddenException -> R.string.error_exceed_api_limit
                        is UnknownHostException -> R.string.error_network_error
                        else -> R.string.error_unknown
                    }
                    OkAlertDialog(titleResId = titleResId, bodyResId = bodyResId)
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
                    .data(data = userDetail.avatarUrl)
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