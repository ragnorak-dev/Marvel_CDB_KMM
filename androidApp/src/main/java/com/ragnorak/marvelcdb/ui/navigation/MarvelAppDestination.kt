package com.ragnorak.marvelcdb.ui.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavBackStackEntry
import com.ragnorak.marvelcdb.android.R
import kotlinx.serialization.Serializable

interface Route

@Serializable
object HeroesList : Route
@Serializable
data class HeroDetails(val heroCode: String) : Route
@Serializable
object Favorites : Route

data class MarvelTopLevelDestination(
    val route: Route,
    val icon: ImageVector,
    val selectedColor: Color,
    val unselectedColor: Color,
    val iconTextId: Int
)

@Composable
fun getTopDestinations() = listOf(
    MarvelTopLevelDestination(
        route = HeroesList,
        icon = ImageVector.vectorResource(id = R.drawable.hero_icon),
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.secondary,
        iconTextId = R.string.menu_heroes
    ),
    MarvelTopLevelDestination(
        route = Favorites,
        icon =ImageVector.vectorResource(id = R.drawable.favorite_icon),
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.secondary,
        iconTextId = R.string.menu_favorites
    )
)