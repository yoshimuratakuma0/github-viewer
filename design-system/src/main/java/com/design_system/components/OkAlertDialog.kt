package com.design_system.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun OkAlertDialog(title: String, body: String) {
    val isShowing = remember { mutableStateOf(true) }
    if (isShowing.value) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(title)
            },
            text = {
                Text(body)
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