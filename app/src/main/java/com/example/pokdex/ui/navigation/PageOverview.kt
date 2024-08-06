package com.example.pokdex.ui.navigation

import androidx.annotation.StringRes
import com.example.pokdex.R

enum class PageOverview(@StringRes val title: Int) {
    SplashScreen(title = R.string.SplashScreen),
    PokedexSummaries(title = R.string.PokedexSummaries),
    PokemonDetail(title = R.string.PokemonDetail),
    UserProfile(title = R.string.UserProfile),
}
