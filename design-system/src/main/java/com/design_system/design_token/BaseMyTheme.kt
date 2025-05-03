package com.design_system.design_token

import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun BaseMyTheme(
    color: MyColor = MyTheme.color,
    typography: MyTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMyColor provides color,
        LocalMyTypography provides typography,
    ) {
        ProvideTextStyle(value = typography.bodyLarge) {
            content()
        }
    }
}