package com.free.design_system.components

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.free.design_system.design_token.MyTheme

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
) {
    androidx.compose.material3.VerticalDivider(
        color = MyTheme.color.border,
        thickness = thickness,
        modifier = modifier,
    )
}

@Preview
@Composable
fun VerticalDividerPreview() {
    MyTheme {
        Surface {
            VerticalDivider()
        }
    }
}