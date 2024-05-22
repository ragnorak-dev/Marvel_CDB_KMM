package com.ragnorak.marvelcdb.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.ui.MarvelAppState
import com.ragnorak.marvelcdb.ui.MarvelCardListViewModel
import com.ragnorak.marvelcdb.ui.ViewState
import com.ragnorak.marvelcdb.ui.favouritesHeroes.FavouritesHeroesView
import com.ragnorak.marvelcdb.ui.heroDetails.MarvelHeroDetails
import com.ragnorak.marvelcdb.ui.heroesList.MarvelHeroesList
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelAppNavHost(
    appState: MarvelAppState,
    viewModel: MarvelCardListViewModel = koinViewModel(),
) {
    SharedTransitionLayout {
        NavHost(
            navController = appState.navController,
            startDestination = Route.HEROES_LIST
        ) {
            composable(Route.HEROES_LIST) {
                MarvelHeroesList(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    viewModel = viewModel
                ) { heroCode ->
                    appState.navController.navigate(Route.HERO_DETAILS.plus("/$heroCode"))
                }
            }
            composable(Route.HERO_DETAILS.plus("/{heroCode}")) { backStackEntry ->
                val heroCode = backStackEntry.arguments?.getString("heroCode")
                val marvelHeroModel =
                    (viewModel.marvelCardList.value as ViewState.Success<List<MarvelCardModel>>)
                        .data.find { it.code == heroCode }
                if (marvelHeroModel != null) {
                    MarvelHeroDetails(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                        model = marvelHeroModel,
                        addFavouriteAction = { viewModel.addMarvelFavouriteCard(marvelHeroModel) },
                        deleteFavouriteAction = { viewModel.deleteMarvelFavouriteCard(marvelHeroModel) }
                    )
                }
            }
            composable(Route.FAVORITES) {
                FavouritesHeroesView(
                    favouritesHeroesFlow = viewModel.marvelCardFavoriteList,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    deleteAction = { viewModel.deleteMarvelFavouriteCard(it) },
                    navigationDetailAction = { heroCode ->
                        appState.navController.navigate(Route.HERO_DETAILS.plus("/$heroCode"))
                    }
                )
            }
        }
    }
}