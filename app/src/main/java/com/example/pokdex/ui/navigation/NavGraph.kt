package com.example.pokdex.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.pokdex.R
import com.example.pokdex.ui.navigation.PageOverview.PokedexSummaries
import com.example.pokdex.ui.navigation.PageOverview.PokemonDetail
import com.example.pokdex.ui.navigation.PageOverview.SplashScreen
import com.example.pokdex.ui.views.PokemonDetailView
import com.example.pokdex.ui.views.SplashScreenView
import com.example.pokdex.ui.views.SummaryView
import com.example.pokdex.ui.views.components.PokeScaffold

object NavGraph {
    @Composable
    fun NavBar(navController: NavHostController) {
        NavigationBar(modifier = Modifier.height(60.dp)) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            NavigationBarItem(
                selected = currentRoute == PokedexSummaries.name,
                onClick = { navController.navigate(PokedexSummaries.name) },
                icon = { Image(painter = painterResource(id = R.drawable.pok__ball_icon_svg), contentDescription = "Summaries", Modifier.height(50.dp).width(50.dp)) },
            )
        }
    }

    @Composable
    fun CreateNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = SplashScreen.name,
        ) {
            composable(PokedexSummaries.name) {
                PokeScaffold(view = { SummaryView(navigateToPokemon = { navController.navigate("${PokemonDetail.name}/$it") }) }, navHostController = navController)
            }
            composable(SplashScreen.name) {
                SplashScreenView(navigateToSummaries = { navController.navigate(PokedexSummaries.name) })
            }
            composable("${PokemonDetail.name}/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
                PokeScaffold(view = { PokemonDetailView(navigateToPokemon = { navController.navigate("${PokemonDetail.name}/$it") }) }, navHostController = navController)
            }
        }
    }
}
