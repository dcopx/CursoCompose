package com.example.cursocompose.composables.gifImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size


@Composable
fun GifImage() {
    val url = "https://i.pinimg.com/originals/31/ff/08/31ff08cf4b98e6284b9cda90dc6c74fc.gif"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .decoderFactory(ImageDecoderDecoder.Factory())
            .build()
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )
}