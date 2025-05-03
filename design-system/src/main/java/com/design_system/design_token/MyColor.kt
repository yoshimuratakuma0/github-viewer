package com.design_system.design_token

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

@Stable
class MyColor(
    primary: Color,
    background: Color,
    error: Color,
    onPrimary: Color,
    onBackground: Color,
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        private set

    var background by mutableStateOf(background, structuralEqualityPolicy())
        private set

    var error by mutableStateOf(error, structuralEqualityPolicy())
        private set

    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        private set

    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        private set
}

fun lightMyColor(
    primary: Color = MyColorLightTokens.Primary,
    background: Color = MyColorLightTokens.Background,
    error: Color = MyColorLightTokens.Error,
    onPrimary: Color = MyColorLightTokens.OnPrimary,
    onBackground: Color = MyColorLightTokens.OnBackground,
): MyColor = MyColor(
    primary = primary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onBackground = onBackground,
)

fun darkMyColor(
    primary: Color = MyColorDarkTokens.Primary,
    background: Color = MyColorDarkTokens.Background,
    error: Color = MyColorDarkTokens.Error,
    onPrimary: Color = MyColorDarkTokens.OnPrimary,
    onBackground: Color = MyColorDarkTokens.OnBackground,
): MyColor = MyColor(
    primary = primary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onBackground = onBackground,
)

internal val LocalMyColor = staticCompositionLocalOf {
    lightMyColor()
}