package com.design_system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection

fun combinePadding(p1: PaddingValues, p2: PaddingValues): PaddingValues {
    return PaddingValues(
        start = p1.calculateStartPadding(LayoutDirection.Ltr) + p2.calculateStartPadding(
            LayoutDirection.Ltr
        ),
        top = p1.calculateTopPadding() + p2.calculateTopPadding(),
        end = p1.calculateEndPadding(LayoutDirection.Ltr) + p2.calculateEndPadding(LayoutDirection.Ltr),
        bottom = p1.calculateBottomPadding() + p2.calculateBottomPadding()
    )
}
