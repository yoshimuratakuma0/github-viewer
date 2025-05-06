package com.free.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.design_system.annotations.NightModePreviewAnnotation
import com.design_system.components.Button
import com.design_system.components.CircularProgressIndicator
import com.design_system.components.Icon
import com.design_system.components.IconButton
import com.design_system.components.Icons
import com.design_system.components.Scaffold
import com.design_system.components.Text
import com.design_system.components.TopAppBar
import com.design_system.components.VerticalDivider
import com.design_system.design_token.MyTheme
import com.free.domain.entities.UserDetail
import com.free.feature_core.components.AsyncRoundedImage
import com.free.githubviewer.R
import com.free.presentation.previews.GithubUserDetailPreviewParameterProvider
import com.free.presentation.viewmodels.GithubUserDetailUiState
import com.free.presentation.viewmodels.GithubUserDetailViewModel

@Composable
fun GithubUserDetailScreen(
    viewModel: GithubUserDetailViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    GithubUserDetailStatelessScreen(
        uiState = uiState,
        onRetry = {
            viewModel.fetchUserDetail()
        },
        onBack = onBack,
    )
}

@Composable
fun GithubUserDetailStatelessScreen(
    uiState: GithubUserDetailUiState,
    onRetry: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_github_user_detail_screen))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = { contentPadding ->
            when (uiState) {
                is GithubUserDetailUiState.Success -> {
                    GithubUserDetailScreen(
                        userDetail = uiState.userDetail,
                        contentPaddingValues = contentPadding,
                    )
                }

                is GithubUserDetailUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is GithubUserDetailUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = stringResource(id = R.string.error_unexpected))
                        Button(onClick = onRetry) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun GithubUserDetailScreen(
    userDetail: UserDetail,
    contentPaddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPaddingValues),
    ) {
        ProfileSummary(
            modifier = Modifier.padding(8.dp),
            userDetail = userDetail,
        )

        VerticalDivider()

        ProfileDetail(
            modifier = Modifier.padding(8.dp),
            userDetail = userDetail,
        )
    }
}

@Composable
private fun ProfileSummary(
    modifier: Modifier = Modifier,
    userDetail: UserDetail,
) {
    val iconRadius = 64
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncRoundedImage(
            modifier = Modifier
                .size((iconRadius * 2).dp),
            url = userDetail.user.avatarUrl,
            placeholderPainter = painterResource(id = R.drawable.ic_account_circle),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = userDetail.displayName,
                style = MyTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.about_followers).format(userDetail.followers),
            )

            Text(
                text = stringResource(id = R.string.about_following).format(userDetail.following),
            )
        }
    }
}

@Composable
private fun ProfileDetail(
    modifier: Modifier = Modifier,
    userDetail: UserDetail
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (userDetail.hasEmail) {
            Text(
                text = stringResource(id = R.string.about_email).format(userDetail.email),
            )
        }
        if (userDetail.hasCompany) {
            Text(
                text = stringResource(id = R.string.about_company).format(userDetail.company),
            )
        }
        if (userDetail.hasBio) {
            Text(
                text = stringResource(id = R.string.about_bio).format(userDetail.bio),
            )
        }
        Text(
            text = stringResource(id = R.string.about_updated_at).format(
                userDetail.updatedAt.year,
                userDetail.updatedAt.monthValue,
                userDetail.updatedAt.dayOfMonth
            ),
        )
        Text(
            text = stringResource(id = R.string.about_created_at).format(
                userDetail.createdAt.year,
                userDetail.createdAt.monthValue,
                userDetail.createdAt.dayOfMonth
            ),
        )
    }
}

@NightModePreviewAnnotation
@Composable
fun PreviewGithubUserDetail(
    @PreviewParameter(GithubUserDetailPreviewParameterProvider::class)
    uiState: GithubUserDetailUiState,
) {
    MyTheme {
        GithubUserDetailStatelessScreen(
            uiState = uiState,
            onRetry = {},
            onBack = {},
        )
    }
}