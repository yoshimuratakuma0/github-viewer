package com.free.feature_core.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.design_system.components.AlertDialog
import com.design_system.components.Text
import com.design_system.components.TextButton
import com.free.feature_core.components.ExceptionMappers.errorBodyBy
import com.free.feature_core.components.ExceptionMappers.errorTitleBy


@Composable
fun ErrorAlertDialog(exception: Exception) {
    val isShowing = remember { mutableStateOf(true) }
    if (isShowing.value) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(errorTitleBy(exception))
            },
            text = {
                Text(errorBodyBy(exception))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isShowing.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = null
        )
    }
}
