package com.free.presentation.views

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.free.presentation.R

@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_settings))
                }
            )
        },
        content = {
            SettingList()
        }
    )
}

@Composable
fun SettingList() {
    Text(text = "setting list")
}