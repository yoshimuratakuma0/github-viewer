package com.free.design_system.components

import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.free.design_system.design_token.MyTheme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
) {
    androidx.compose.material3.HorizontalDivider(
        color = MyTheme.color.border,
        thickness = thickness,
        modifier = modifier,
    )
}

@Composable
@Preview
fun HorizontalDividerPreview() {
    HorizontalDivider()
}