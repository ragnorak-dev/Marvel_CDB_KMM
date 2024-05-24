package com.ragnorak.marvelcdb.ui.heroesList

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.components.LoadingComponent
import com.ragnorak.components.marvel.MarvelHeroCard
import com.ragnorak.components.marvel.MarvelHeroCompModel
import com.ragnorak.marvelcdb.android.R
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers
import com.ragnorak.marvelcdb.ui.MarvelCardListViewModel
import com.ragnorak.marvelcdb.ui.ViewState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelHeroesList(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MarvelCardListViewModel,
    navigationDetailAction: (heroCode: String) -> Unit
) {

    val marvelCardListState =
        viewModel.marvelCardList.collectAsState()


    when (marvelCardListState.value) {
        ViewState.Idle -> {
            viewModel.getMarvelCardList()
        }

        ViewState.Loading -> {
            MarvelHeroesListLoading()
        }

        is ViewState.Success -> {
            MarvelHeroesListSuccess(
                marvelCardList = ((marvelCardListState.value as ViewState.Success<List<MarvelCardModel>>).data),
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                navigationDetailAction = navigationDetailAction
            )

        }

        is ViewState.Error -> {
            MarvelHeroesListError(marvelCardListState.value as ViewState.Error)
        }
    }
}

@Composable
private fun MarvelHeroesListLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoadingComponent()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MarvelHeroesListSuccess(
    marvelCardList: List<MarvelCardModel>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigationDetailAction: (heroCode: String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
    ) {

        items(marvelCardList) { hero ->
            MarvelHeroCard(
                modifier = Modifier.testTag(ConstansUiIdentifiers.MARVEL_CARD_LIST),
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                hero = MarvelHeroCompModel(
                    name = hero.name,
                    faction = hero.factionCode,
                    imageUrl = hero.imagesrc,
                    isFavourite = hero.isFavourite,
                    favouriteIcon = ImageVector.vectorResource(id = R.drawable.favorite_icon)
                )
            ) {
                navigationDetailAction(hero.code)
            }
        }
    }
}

@Composable
private fun MarvelHeroesListError(error: ViewState.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = error.errorMessage)
    }
}