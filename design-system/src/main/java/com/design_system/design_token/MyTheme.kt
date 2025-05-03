package com.design_system.design_token

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object MyTheme {
    val color: MyColor
        @Composable
        @ReadOnlyComposable
        get() = LocalMyColor.current

    val typography: MyTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMyTypography.current
}