package com.example.pokdex.ui

import com.example.pokdex.rules.TestDispatcherRule
import com.example.pokdex.testRepositories.TestPokemonDetailRepository
import com.example.pokdex.testRepositories.TestUserAndPokemonRepository
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserProfileViewModelTest {

    val testUserAndPokemonRepository = TestUserAndPokemonRepository()
    val testPokemonDetailRepository = TestPokemonDetailRepository()
    var viewModel: UserProfileViewModel? = null

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun run() {
        viewModel = UserProfileViewModel(testUserAndPokemonRepository, testPokemonDetailRepository)
    }

    @Test
    fun noNameOnInitiate() {
        assertEquals("", viewModel!!.user.value.name)
    }

    @Test
    fun correctNameInserted() {
        runTest {
            viewModel!!.insertUser("Joachim")
            assertEquals("Joachim", viewModel!!.user.value.name)
        }
    }

    @Test
    fun addAndDeletePokemon() {
        runTest {
            viewModel!!.insertUser("Joachim")
            viewModel!!.addPokemonToParty(1)
            assertEquals("bulbasaur".lowercase(), viewModel!!.user.value.pokemonParty?.first { pkmn -> pkmn?.name?.lowercase() == "bulbasaur" }?.name?.lowercase())
            viewModel!!.deletePokemonFromParty(viewModel!!.user.value.pokemonParty?.first { pkmn -> pkmn?.name?.lowercase() == "bulbasaur" }!!)
            assertEquals(0, viewModel!!.user.value.pokemonParty?.size)
        }
    }
}
