package com.example.pokdex.ui.views.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.pokdex.R
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel

@Composable
fun DetailGif(pokemonDetailViewModel: PokemonDetailViewModel, modifier: Modifier) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    var index = pokemonDetailViewModel.pokemon.collectAsState().value.index
    var url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/showdown/$index.gif"
    var request = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = url)
            .apply(block = fun ImageRequest.Builder.() {
                size(Size.ORIGINAL)
            }).build(),
        imageLoader = imageLoader,
        error = painterResource(R.drawable.pok__ball_icon_svg),

    )

    Image(
        painter = request,
        contentDescription = null,
        modifier = modifier
            .width(200.dp)
            .height(200.dp),
    )
}
