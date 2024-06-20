package com.free.presentation.previews

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "night mode yes",
    group = "night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "night mode no",
    group = "night mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class NightModePreviewAnnotation