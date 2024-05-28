package com.ragnorak.marvelcdb.ui.heroDetails

import android.content.Context
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.ragnorak.marvelcdb.android.R
import com.ragnorak.marvelcdb.di.stubCommonModule
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers.MARVEL_CARD_DETAILS
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers.MARVEL_CARD_DETAILS_IMAGE
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers.MARVEL_CARD_DETAILS_INFO
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers.MARVEL_CARD_DETAILS_NAME
import com.ragnorak.marvelcdb.ui.navigation.MarvelAppNavHost
import com.ragnorak.marvelcdb.ui.rememberMarvelAppState
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import kotlin.test.Test


class MarvelHeroDetailsTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        loadKoinModules(stubCommonModule)
        composeTestRule.setContent {
            MarvelAppNavHost(appState = rememberMarvelAppState(rememberNavController()))
        }
        composeTestRule.onAllNodesWithTag(ConstansUiIdentifiers.MARVEL_CARD_LIST)[0].performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test_marvel_hero_details_screen_is_displayed() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(MARVEL_CARD_DETAILS))
    }

    @Test
    fun test_marvel_hero_details_screen_has_not_image_display() {
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_IMAGE).assertDoesNotExist()
    }

    @Test
    fun test_marvel_hero_details_screen_has_marvel_hero_name() {
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_NAME).assertIsDisplayed()
    }

    @Test
    fun test_marvel_hero_details_screen_has_marvel_hero_info_in_correct_order() {
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_INFO).assertIsDisplayed()
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_INFO).onChildAt(0)
            .assertTextContains("${context.getString(R.string.attack)}: 0")
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_INFO).onChildAt(1)
            .assertTextContains("${context.getString(R.string.defense)}: 0")
        composeTestRule.onNodeWithTag(MARVEL_CARD_DETAILS_INFO).onChildAt(2)
            .assertTextContains("${context.getString(R.string.health)}: 0")

    }

}