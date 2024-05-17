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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ragnorak.marvelcdb.ui.heroesList.MarvelHeroesList
import com.ragnorak.marvelcdb.ui.navigation.MarvelAppNavHost
import com.ragnorak.marvelcdb.ui.navigation.Route
import com.ragnorak.marvelcdb.ui.navigation.getTopDestinations

@Composable
fun MarvelApp(viewModel: MarvelCardListViewModel) {


    Scaffold(
        bottomBar = {
            MarvelBottomAppBar()
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MarvelAppNavHost(viewModel)

        }
    }
}

@Composable
private fun MarvelBottomAppBar() {
    val selectedDestination = remember { mutableStateOf(Route.HEROES_LIST) }

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        getTopDestinations().forEach { marvelDestination ->
            NavigationBarItem(
                selected = selectedDestination.value == marvelDestination.route,
                onClick = { selectedDestination.value = marvelDestination.route },
                icon = {
                    Icon(
                        imageVector = marvelDestination.icon,
                        contentDescription = stringResource(id = marvelDestination.iconTextId),
                        tint = if (selectedDestination.value == marvelDestination.route) {
                            marvelDestination.selectedColor
                        } else {
                            marvelDestination.unselectedColor
                        },
                        modifier = Modifier.size(36.dp)
                    )
                }
            )
        }
    }

}