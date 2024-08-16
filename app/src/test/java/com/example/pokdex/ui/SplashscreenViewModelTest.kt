package com.example.pokdex.ui
import com.example.pokdex.testRepositories.TestAbilityRepository
import com.example.pokdex.testRepositories.TestApiVersionRepository
import com.example.pokdex.testRepositories.TestMoveRepository
import com.example.pokdex.testRepositories.TestPokemonDetailRepository
import com.example.pokdex.testRepositories.TestPokemonSummaryRepository
import com.example.pokdex.ui.viewmodels.SplashScreenViewModel

class SplashscreenViewModelTest {
    private val testPokemonSummaryRepository = TestPokemonSummaryRepository()
    private val testAbilityRepository = TestAbilityRepository()
    private val testApiVersionRepository = TestApiVersionRepository()
    private val testMoveRepository = TestMoveRepository()
    private val testPokemonDetailRepository = TestPokemonDetailRepository()

    private val viewModel = SplashScreenViewModel(testApiVersionRepository, testPokemonSummaryRepository, testMoveRepository, testAbilityRepository, testPokemonDetailRepository)

    // this viewmodel has 2 purposes. 1 retrieve data from the server 2 show a loadscreen. No real tests can be performed.
}
