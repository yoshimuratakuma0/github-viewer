package com.free.feature_user.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.feature_user.viewmodels.UsersUiState

class UsersPreviewParameterProvider : PreviewParameterProvider<UsersUiState> {
    override val values: Sequence<UsersUiState>
        get() = sequenceOf(
            UsersUiState.Error(Exception()),
//            GitHubUsersUiState.Loading,
            UsersUiState.Success,
        )

}