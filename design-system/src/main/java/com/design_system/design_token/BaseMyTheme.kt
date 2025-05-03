package com.design_system.design_token

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    BaseMyTheme(
        color = if (darkTheme) {
            darkMyColor()
        } else {
            lightMyColor()
        },
        typography = MyTypography(),
        content = content
    )
}

@Composable
private fun BaseMyTheme(
    color: MyColor = MyTheme.color,
    typography: MyTypography = MyTheme.typography,
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