package com.free.presentation.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.presentation.viewmodels.GitHubUsersUiState

class GitHubUsersPreviewParameterProvider : PreviewParameterProvider<GitHubUsersUiState> {
    override val values: Sequence<GitHubUsersUiState>
        get() = sequenceOf(
            GitHubUsersUiState.Error(Exception()),
            GitHubUsersUiState.Loading,
            GitHubUsersUiState.Success,
        )

}