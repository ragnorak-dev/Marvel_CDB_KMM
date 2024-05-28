package com.ragnorak.marvelcdb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.ragnorak.marvelcdb.ui.navigation.Favorites
import com.ragnorak.marvelcdb.ui.navigation.HeroesList
import com.ragnorak.marvelcdb.ui.navigation.Route

@Composable
fun rememberMarvelAppState(navController: NavHostController) = remember(navController) {
    MarvelAppState(navController)
}

class MarvelAppState(val navController: NavHostController) {


    /* WorkAround to get the current route safe type,
    it need to add the Route implementation classes to -keepnames in proguard*/
    val shouldShowBottomBar: Boolean
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.let {
            val type = it.destination.route
            when (type?.substringAfterLast(".")?.substringBefore("/")) {
                HeroesList::class.simpleName -> true
                Favorites::class.simpleName -> true
                else -> false
            }
        } ?: false



    fun navigateTopLevelDestination(topLevelDestination: Route) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            HeroesList -> navController.navigate(HeroesList, topLevelNavOptions)
            Favorites -> navController.navigate(Favorites, topLevelNavOptions)
        }
    }
}