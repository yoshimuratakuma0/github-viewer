package com.free.presentation.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.free.githubviewer.R

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
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = null
        )
    }
}