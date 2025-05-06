package com.free.feature_core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter


@Composable
fun AsyncRoundedImage(
    modifier: Modifier = Modifier,
    url: String,
    placeholderPainter: Painter,
) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(
            model = url,
            placeholder = placeholderPainter,
            error = placeholderPainter
        ),
        contentDescription = null,
    )
}
