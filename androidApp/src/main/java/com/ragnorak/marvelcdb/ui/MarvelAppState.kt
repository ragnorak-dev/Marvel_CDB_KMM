package com.ragnorak.marvelcdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.ragnorak.marvelcdb.ui.navigation.Route

@Composable
fun rememberMarvelAppState(navController: NavHostController) = remember(navController) {
    MarvelAppState(navController)
}

class MarvelAppState(val navController: NavHostController) {

    val currentTopLevelDestination: String?
        get() = navController.currentDestination?.route


    val shouldShowBottomBar: Boolean
        @Composable
        get() = when(navController.currentBackStackEntryAsState().value?.destination?.route) {
            Route.HEROES_LIST -> true
            Route.FAVORITES -> true
            else -> false
        }

    fun navigateTopLevelDestination(topLevelDestination: String) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            Route.HEROES_LIST -> navController.navigate(Route.HEROES_LIST, topLevelNavOptions)
            Route.FAVORITES -> navController.navigate(Route.FAVORITES, topLevelNavOptions)
        }
    }
}