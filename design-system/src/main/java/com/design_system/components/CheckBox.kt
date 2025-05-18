package com.free.design_system.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.free.design_system.annotations.NightModePreviewAnnotation
import com.free.design_system.design_token.MyTheme

@Composable
fun CheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    androidx.compose.material3.Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MyTheme.color.primary,
            uncheckedColor = MyTheme.color.onBackground,
            checkmarkColor = MyTheme.color.background,
            disabledCheckedColor = MyTheme.color.onDisabledBackground,
            disabledUncheckedColor = MyTheme.color.onDisabledBackground,
            disabledIndeterminateColor = MyTheme.color.onDisabledBackground
        ),
        interactionSource = interactionSource,
    )
}

@NightModePreviewAnnotation
@Composable
private fun CheckBoxPreview(
    @PreviewParameter(CheckBoxPreviewParameterProvider::class) state: CheckBoxState
) {
    MyTheme {
        Surface {
            CheckBox(
                checked = state.checked,
                onCheckedChange = null,
                enabled = state.enabled
            )
        }
    }
}

private data class CheckBoxState(
    val checked: Boolean,
    val enabled: Boolean
)

private class CheckBoxPreviewParameterProvider : PreviewParameterProvider<CheckBoxState> {
    override val values: Sequence<CheckBoxState>
        get() = sequenceOf(
            CheckBoxState(checked = true, enabled = true),
            CheckBoxState(checked = false, enabled = true),
            CheckBoxState(checked = true, enabled = false),
            CheckBoxState(checked = false, enabled = false)
        )
}