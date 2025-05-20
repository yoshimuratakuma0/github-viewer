package com.free.feature_user.screens

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
import com.free.design_system.annotations.NightModePreviewAnnotation
import com.free.design_system.components.Button
import com.free.design_system.components.CircularProgressIndicator
import com.free.design_system.components.Icon
import com.free.design_system.components.IconButton
import com.free.design_system.components.Icons
import com.free.design_system.components.Scaffold
import com.free.design_system.components.Text
import com.free.design_system.components.TopAppBar
import com.free.design_system.components.VerticalDivider
import com.free.design_system.design_token.MyTheme
import com.free.feature_core.R
import com.free.feature_core.components.AsyncRoundedImage
import com.free.feature_core.components.ExceptionMappers
import com.free.feature_user.models.UserDetailUiModel
import com.free.feature_user.previews.GitHubUserDetailPreviewParameterProvider
import com.free.feature_user.viewmodels.GitHubUserDetailUiState
import com.free.feature_user.viewmodels.GitHubUserDetailViewModel
import java.time.LocalDateTime

@Composable
fun GitHubUserDetailScreen(
    viewModel: GitHubUserDetailViewModel,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    GitHubUserDetailStatelessScreen(
        uiState = uiState,
        onRetry = {
            viewModel.fetchUserDetail()
        },
        onBack = onBack,
    )
}

@Composable
fun GitHubUserDetailStatelessScreen(
    uiState: GitHubUserDetailUiState,
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
                is GitHubUserDetailUiState.Success -> {
                    GitHubUserDetailScreen(
                        uiModel = uiState.userDetailUiModel,
                        contentPaddingValues = contentPadding,
                    )
                }

                is GitHubUserDetailUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is GitHubUserDetailUiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = ExceptionMappers.errorTitleBy(uiState.exception))
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
private fun GitHubUserDetailScreen(
    uiModel: UserDetailUiModel,
    contentPaddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPaddingValues),
    ) {
        ProfileSummary(
            modifier = Modifier.padding(8.dp),
            displayName = uiModel.displayName,
            followers = uiModel.followers,
            following = uiModel.following,
            avatarUrl = uiModel.avatarUrl,
        )

        VerticalDivider()

        ProfileDetail(
            modifier = Modifier.padding(8.dp),
            emailText = uiModel.emailText,
            companyText = uiModel.companyText,
            bioText = uiModel.bioText,
            lastActivityAt = uiModel.lastActivityAt,
            accountCreationAt = uiModel.accountCreationAt,
        )
    }
}

@Composable
private fun ProfileSummary(
    modifier: Modifier = Modifier,
    displayName: String,
    followers: Int,
    following: Int,
    avatarUrl: String,
) {
    val iconRadius = 64
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncRoundedImage(
            modifier = Modifier
                .size((iconRadius * 2).dp),
            url = avatarUrl,
            placeholderPainter = painterResource(id = R.drawable.ic_account_circle),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = displayName,
                style = MyTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.about_followers).format(followers),
            )

            Text(
                text = stringResource(id = R.string.about_following).format(following),
            )
        }
    }
}

@Composable
private fun ProfileDetail(
    modifier: Modifier = Modifier,
    emailText: String?,
    companyText: String?,
    bioText: String?,
    lastActivityAt: LocalDateTime,
    accountCreationAt: LocalDateTime,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        emailText?.let {
            Text(
                text = stringResource(id = R.string.about_email).format(it),
            )
        }

        companyText?.let {
            Text(
                text = stringResource(id = R.string.about_company).format(it),
            )
        }

        bioText?.let {
            Text(
                text = stringResource(id = R.string.about_bio).format(it),
            )
        }


        Text(
            text = stringResource(id = R.string.about_updated_at).format(
                lastActivityAt.year,
                lastActivityAt.monthValue,
                lastActivityAt.dayOfMonth
            ),
        )
        Text(
            text = stringResource(id = R.string.about_created_at).format(
                accountCreationAt.year,
                accountCreationAt.monthValue,
                accountCreationAt.dayOfMonth
            ),
        )
    }
}

@NightModePreviewAnnotation
@Composable
fun PreviewGitHubUserDetail(
    @PreviewParameter(GitHubUserDetailPreviewParameterProvider::class)
    uiState: GitHubUserDetailUiState,
) {
    MyTheme {
        GitHubUserDetailStatelessScreen(
            uiState = uiState,
            onRetry = {},
            onBack = {},
        )
    }
}