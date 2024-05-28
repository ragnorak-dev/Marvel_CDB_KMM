package com.ragnorak.marvelcdb.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
            startDestination = HeroesList
        ) {
            composable<HeroesList> {
                MarvelHeroesList(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    viewModel = viewModel
                ) { heroCode ->
                    appState.navController.navigate(HeroDetails(heroCode = heroCode))
                }
            }
            composable<HeroDetails> { backStackEntry ->
                val heroCode = backStackEntry.toRoute<HeroDetails>()
                val marvelHeroModel =
                    (viewModel.marvelCardList.value as ViewState.Success<List<MarvelCardModel>>)
                        .data.find { it.code == heroCode.heroCode }
                if (marvelHeroModel != null) {
                    MarvelHeroDetails(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                        model = marvelHeroModel,
                        addFavouriteAction = {
                            viewModel.addMarvelFavouriteCard(marvelHeroModel)
                        },
                        deleteFavouriteAction = {
                            viewModel.deleteMarvelFavouriteCard(marvelHeroModel)
                        }
                    )
                }
            }
            composable<Favorites> {
                FavouritesHeroesView(
                    favouritesHeroesFlow = viewModel.marvelCardFavoriteList,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    navigationDetailAction = { heroCode ->
                        appState.navController.navigate(HeroDetails(heroCode = heroCode))
                    }
                )
            }
        }
    }
}