package com.design_system.design_token

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class MyTypography(
    val bodyLarge: TextStyle = MyTypographyTokens.BodyLarge,
    val bodyMedium: TextStyle = MyTypographyTokens.BodyMedium,
    val bodySmall: TextStyle = MyTypographyTokens.BodySmall,
)


internal val LocalMyTypography = staticCompositionLocalOf {
    MyTypography()
}