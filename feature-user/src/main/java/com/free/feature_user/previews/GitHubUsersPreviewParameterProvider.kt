package com.free.feature_user.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.feature_user.viewmodels.GitHubUsersUiState

class GitHubUsersPreviewParameterProvider : PreviewParameterProvider<GitHubUsersUiState> {
    override val values: Sequence<GitHubUsersUiState>
        get() = sequenceOf(
            GitHubUsersUiState.Error(Exception()),
//            GitHubUsersUiState.Loading,
            GitHubUsersUiState.Success,
        )

}