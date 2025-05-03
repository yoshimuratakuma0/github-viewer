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
    disabledPrimary: Color,
    disabledBackground: Color,
    onDisabledBackground: Color,
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

    var disabledPrimary by mutableStateOf(disabledPrimary, structuralEqualityPolicy())
        private set
    
    var disabledBackground by mutableStateOf(disabledBackground, structuralEqualityPolicy())
        private set

    var onDisabledBackground by mutableStateOf(onDisabledBackground, structuralEqualityPolicy())
        private set
}

fun lightMyColor(
    primary: Color = MyColorLightTokens.Primary,
    background: Color = MyColorLightTokens.Background,
    error: Color = MyColorLightTokens.Error,
    onPrimary: Color = MyColorLightTokens.OnPrimary,
    onBackground: Color = MyColorLightTokens.OnBackground,
    disabledPrimary: Color = MyColorLightTokens.DisabledPrimary,
    disabledBackground: Color = MyColorLightTokens.DisabledBackground,
    onDisabledBackground: Color = MyColorLightTokens.OnDisabledBackground,
): MyColor = MyColor(
    primary = primary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onBackground = onBackground,
    disabledPrimary = disabledPrimary,
    disabledBackground = disabledBackground,
    onDisabledBackground = onDisabledBackground,
)

fun darkMyColor(
    primary: Color = MyColorDarkTokens.Primary,
    background: Color = MyColorDarkTokens.Background,
    error: Color = MyColorDarkTokens.Error,
    onPrimary: Color = MyColorDarkTokens.OnPrimary,
    onBackground: Color = MyColorDarkTokens.OnBackground,
    disabledPrimary: Color = MyColorDarkTokens.DisabledPrimary,
    disabledBackground: Color = MyColorDarkTokens.DisabledBackground,
    onDisabledBackground: Color = MyColorDarkTokens.OnDisabledBackground,
): MyColor = MyColor(
    primary = primary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onBackground = onBackground,
    disabledPrimary = disabledPrimary,
    disabledBackground = disabledBackground,
    onDisabledBackground = onDisabledBackground,
)

internal val LocalMyColor = staticCompositionLocalOf {
    lightMyColor()
}