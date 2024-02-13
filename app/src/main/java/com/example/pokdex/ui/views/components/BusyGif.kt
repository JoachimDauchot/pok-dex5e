package com.example.pokdex.ui.views.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.pokdex.R

@Composable
fun BusyGif() {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    val index = (0..809).random()
    var url by remember { mutableStateOf("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/$index.gif") }

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    size(Size.ORIGINAL)
                }).build(),
            imageLoader = imageLoader,
            onError = { url = "android.resource://com.example.pokdex/${R.drawable.pok__ball_icon_svg}" },
        ),
        contentDescription = null,
        modifier = Modifier
            .width(250.dp)
            .height(250.dp),
    )
}
