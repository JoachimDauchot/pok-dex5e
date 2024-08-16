package com.example.pokdex.ui

import com.example.pokdex.rules.TestDispatcherRule
import com.example.pokdex.testRepositories.TestPokemonSummaryRepository
import com.example.pokdex.ui.viewmodels.SummaryViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Locale

class SummaryViewModelTest {
    val testPokemonSummaryRepository = TestPokemonSummaryRepository()
    var viewModel: SummaryViewModel? = null

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun run() {
        viewModel = SummaryViewModel(testPokemonSummaryRepository)
    }

    @Test
    fun correctAmountOfSummaries() {
        assertEquals(3, viewModel!!.summaries.value.size)
    }

    @Test
    fun correctContent() {
        assertEquals("Bulbasaur".lowercase(Locale.getDefault()), viewModel!!.summaries.value.first { summary -> summary.name.lowercase() == "Bulbasaur".lowercase() }.name.lowercase(Locale.getDefault()))
    }
}
