package com.ragnorak.marvelcdb.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.ragnorak.components.foundations.Dimensions
import com.ragnorak.marvelcdb.ui.navigation.MarvelAppNavHost
import com.ragnorak.marvelcdb.ui.navigation.Route
import com.ragnorak.marvelcdb.ui.navigation.getTopDestinations

@Composable
fun MarvelApp() {
    val appState = rememberMarvelAppState(rememberNavController())
    Scaffold(
        bottomBar = {
            MarvelBottomAppBar(appState = appState)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MarvelAppNavHost(appState = appState)
        }
    }
}

@Composable
private fun MarvelBottomAppBar(appState: MarvelAppState) {
    var selectedDestination by remember { mutableStateOf(Route.HEROES_LIST) }

    if (appState.shouldShowBottomBar) {
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
            getTopDestinations().forEach { marvelDestination ->
                NavigationBarItem(
                    selected = selectedDestination == marvelDestination.route,
                    onClick = {
                        selectedDestination = marvelDestination.route
                        appState.navigateTopLevelDestination(marvelDestination.route)
                    },
                    icon = {
                        Icon(
                            imageVector = marvelDestination.icon,
                            contentDescription = stringResource(id = marvelDestination.iconTextId),
                            tint = if (selectedDestination == marvelDestination.route) {
                                marvelDestination.selectedColor
                            } else {
                                marvelDestination.unselectedColor
                            },
                            modifier = Modifier.size(Dimensions.xl)
                        )
                    }
                )
            }
        }
    }
}