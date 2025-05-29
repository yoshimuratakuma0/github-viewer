package com.free.feature_user.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.feature_user.models.UserDetailUiModel
import com.free.feature_user.viewmodels.GitHubUserDetailUiState
import java.time.LocalDateTime

class GitHubUserDetailPreviewParameterProvider : PreviewParameterProvider<GitHubUserDetailUiState> {
    override val values: Sequence<GitHubUserDetailUiState>
        get() = sequenceOf(
            GitHubUserDetailUiState.Error(Exception()),
//            GitHubUserDetailUiState.Loading,
            GitHubUserDetailUiState.Success(
                userDetailUiModel = UserDetailUiModel(
                    avatarUrl = "https://localhost/u/1?v=4",
                    displayName = "preview name",
                    followers = 12345,
                    following = 23456,
                    companyText = "preview company",
                    emailText = "",
                    bioText = "this is bio. ",
                    lastActivityAt = LocalDateTime.MIN,
                    accountCreationAt = LocalDateTime.MAX,
                )
            ),
            GitHubUserDetailUiState.Success(
                userDetailUiModel = UserDetailUiModel(
                    avatarUrl = "https://localhost/u/1?v=4",
                    displayName = "long name long name long name long name",
                    followers = 12345,
                    following = 23456,
                    companyText = "preview company",
                    emailText = "sample@gmail.com",
                    bioText = "this is bio. \n\n\n\n\n長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio\n\n\n\n\n\n\n\n\n長いbio",
                    lastActivityAt = LocalDateTime.MIN,
                    accountCreationAt = LocalDateTime.MAX,
                )
            ),
            GitHubUserDetailUiState.Success(
                userDetailUiModel = UserDetailUiModel(
                    avatarUrl = "https://localhost/u/1?v=4",
                    displayName = "preview name",
                    followers = 0,
                    following = 0,
                    companyText = null,
                    emailText = null,
                    bioText = null,
                    lastActivityAt = LocalDateTime.MIN,
                    accountCreationAt = LocalDateTime.MAX,
                )
            )
        )
}