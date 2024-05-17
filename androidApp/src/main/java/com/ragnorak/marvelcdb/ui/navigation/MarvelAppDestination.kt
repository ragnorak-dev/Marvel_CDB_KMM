package com.ragnorak.marvelcdb.ui.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ragnorak.marvelcdb.android.R

object Route {
    const val HEROES_LIST = "heroesList"
    const val HERO_DETAILS = "heroDetails"
    const val FAVORITES = "Favorites"
}

data class MarvelTopLevelDestination(
    val route: String,
    val icon: ImageVector,
    val selectedColor: Color,
    val unselectedColor: Color,
    val iconTextId: Int
)

@Composable
fun getTopDestinations() = listOf(
    MarvelTopLevelDestination(
        route = Route.HEROES_LIST,
        icon = ImageVector.vectorResource(id = R.drawable.hero_icon),
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.secondary,
        iconTextId = R.string.menu_heroes
    ),
    MarvelTopLevelDestination(
        route = Route.FAVORITES,
        icon =ImageVector.vectorResource(id = R.drawable.favorite_icon),
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.secondary,
        iconTextId = R.string.menu_favorites
    )
)