package com.free.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.free.domain.exceptions.FetchUsersException
import com.free.githubviewer.R
import java.net.UnknownHostException

@Composable
fun errorTitleBy(exception: Exception): String {
    val titleResId = when (exception) {
        is FetchUsersException.Forbidden -> R.string.error_title_exceed_api_limit
        is UnknownHostException -> R.string.error_title_network_error
        else -> R.string.error_title_unexpected
    }
    return stringResource(id = titleResId)
}

@Composable
fun errorBodyBy(exception: Exception): String {
    val bodyResId = when (exception) {
        is FetchUsersException.Forbidden -> R.string.error_exceed_api_limit
        is UnknownHostException -> R.string.error_network_error
        else -> R.string.error_unexpected
    }
    return stringResource(id = bodyResId)
}