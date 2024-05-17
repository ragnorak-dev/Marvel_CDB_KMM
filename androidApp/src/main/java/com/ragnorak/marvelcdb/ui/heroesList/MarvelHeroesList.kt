package com.ragnorak.marvelcdb.ui.heroesList

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ragnorak.components.LoadingComponent
import com.ragnorak.components.marvel.MarvelHeroCard
import com.ragnorak.components.marvel.MarvelHeroCompModel
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingComponent()
            }
        }

        is ViewState.Success -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
            ) {

                items(
                    (marvelCardListState.value as ViewState.Success<List<MarvelCardModel>>)
                        .data
                ) { hero ->
                    MarvelHeroCard(
                        sharedTransitionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope,
                        hero = MarvelHeroCompModel(
                            hero.name,
                            hero.factionCode,
                            hero.imagesrc
                        )
                    ) {
                        navigationDetailAction(hero.code)
                    }
                }
            }
        }

        is ViewState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (marvelCardListState.value as ViewState.Error).errorMessage)
            }
        }
    }
}