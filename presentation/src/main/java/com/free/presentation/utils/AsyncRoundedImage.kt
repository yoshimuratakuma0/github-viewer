package com.free.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.free.githubviewer.R


@Composable
fun AsyncRoundedImage(
    iconRadius: Int,
    url: String,
) {
    Box(
        modifier = Modifier.size((2 * iconRadius).dp)
    ) {
        Icon(
            painterResource(id = R.drawable.ic_account_circle),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url)
                    .apply(block = {
                        transformations(
                            with(LocalDensity.current) {
                                RoundedCornersTransformation(
                                    topLeft = iconRadius.dp.toPx(),
                                    topRight = iconRadius.dp.toPx(),
                                    bottomLeft = iconRadius.dp.toPx(),
                                    bottomRight = iconRadius.dp.toPx(),
                                )
                            }
                        )
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}