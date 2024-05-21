package com.ragnorak.marvelcdb.ui.heroesList

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ragnorak.marvelcdb.di.stubCommonModule
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers
import com.ragnorak.marvelcdb.ui.navigation.Route
import org.junit.Before
import org.junit.Rule
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.loadKoinModules
import kotlin.test.Test

class MarvelHeroesListTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    @OptIn(ExperimentalSharedTransitionApi::class)
    fun setup() {
        loadKoinModules(stubCommonModule)
        composeTestRule.setContent {
            val navController = rememberNavController()
            SharedTransitionLayout {
                NavHost(
                    navController = navController,
                    startDestination = Route.HEROES_LIST
                ) {
                    composable(Route.HEROES_LIST) {
                        MarvelHeroesList(
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this@composable,
                            viewModel = koinViewModel(),
                        ) {
                        }
                    }
                }
            }
        }

    }


    @Test
    fun marvelHeroesList_displaysHeroes() {
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[2].assertIsDisplayed()
    }

    @Test
    fun marvelHeroesList_has_excepted_order() {
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[0].assertTextEquals("Iron Man")
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[1].assertTextEquals("Captain America")
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[2].assertTextEquals("Thor")
    }

    @Test
    fun marvelHeroesList_no_avengers_traits_are_not_displayed() {
        composeTestRule.onNodeWithText("Wolverine").assertIsNotDisplayed()
    }

}