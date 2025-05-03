package com.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun AsyncRoundedImage(
    size: Dp,
    url: String,
    placeholderPainter: Painter,
) {
    Box(modifier = Modifier.size(size)) {
        Image(
            painter = rememberAsyncImagePainter(
                model = url,
                placeholder = placeholderPainter,
                error = placeholderPainter
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(size / 2))
        )
    }
}
