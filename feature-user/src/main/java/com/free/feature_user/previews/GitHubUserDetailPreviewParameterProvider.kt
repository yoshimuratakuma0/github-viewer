package com.free.feature_user.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.domain.entities.User
import com.free.domain.entities.UserDetail
import com.free.feature_user.viewmodels.GitHubUserDetailUiState
import java.time.LocalDateTime

class GitHubUserDetailPreviewParameterProvider : PreviewParameterProvider<GitHubUserDetailUiState> {
    override val values: Sequence<GitHubUserDetailUiState>
        get() = sequenceOf(
            GitHubUserDetailUiState.Error(Exception()),
//            GitHubUserDetailUiState.Loading,
            GitHubUserDetailUiState.Success(
                UserDetail(
                    user = User(
                        id = 1,
                        username = "preview name",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                    ),
                    email = null,
                    bio = "短いbio",
                    company = null,
                    createdAt = LocalDateTime.MIN,
                    updatedAt = LocalDateTime.MAX,
                    followers = 1,
                    following = 1,
                    name = null,
                )
            ),
            GitHubUserDetailUiState.Success(
                UserDetail(
                    user = User(
                        id = 1,
                        username = "preview name",
                        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
                    ),
                    email = "sample@gmail.com",
                    bio = "this is bio. \n\n\n\n\n長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio長いbio\n\n\n\n\n\n\n\n\n長いbio",
                    company = "preview company",
                    createdAt = LocalDateTime.MIN,
                    updatedAt = LocalDateTime.MAX,
                    followers = 12345,
                    following = 23456,
                    name = "preview name"
                )
            ),
        )
}