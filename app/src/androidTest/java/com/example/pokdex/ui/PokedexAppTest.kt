package com.example.pokdex.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.pokdex.PokedexAppView
import com.example.pokdex.ui.navigation.PageOverview.PokedexSummaries
import com.example.pokdex.ui.navigation.PageOverview.SplashScreen
import com.example.pokdex.ui.navigation.PageOverview.UserProfile
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokedexAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun initApp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            PokedexAppView(navController)
        }
    }

    @Test
    fun generalNavigationTest() {
        assertEquals(navController.currentBackStackEntry?.destination?.route, SplashScreen.name)
        composeTestRule
            .waitUntil(
                250000,
            ) { navController.currentBackStackEntry?.destination?.route == PokedexSummaries.name }
        composeTestRule
            .onNodeWithContentDescription("Summaries")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Profile")
            .performClick()
        assertEquals(navController.currentBackStackEntry?.destination?.route, UserProfile.name)
        composeTestRule
            .onNodeWithText("Enter Name")
            .performTextInput("Joachim")
        composeTestRule
            .onNodeWithText("OK")
            .performClick()
        composeTestRule
            .onAllNodesWithContentDescription("Add pokemon")[0]
            .performClick()
        composeTestRule
            .onNodeWithText("Add index of pokemon to add")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("CLOSE")
            .assertIsDisplayed()
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Summaries")
            .performClick()
        composeTestRule
            .onAllNodesWithContentDescription("pokemonPixelArt")[0]
            .performClick()
        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithText("Attributes", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Abilities", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Moves", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Evolutions", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}
