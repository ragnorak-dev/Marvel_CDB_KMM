package com.ragnorak.marvelcdb.ui.favouritesHeroes

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ragnorak.components.marvel.MarvelHeroCard
import com.ragnorak.components.marvel.MarvelHeroCompModel
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavouritesHeroesView(
    favouritesHeroesFlow: Flow<List<MarvelCardModel>>,
    deleteAction: (MarvelCardModel) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigationDetailAction: (heroCode: String) -> Unit
) {

    val favouritesHeroesList by favouritesHeroesFlow
        .collectAsStateWithLifecycle(initialValue = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
    ) {

        items(favouritesHeroesList) { hero ->
            MarvelHeroCard(
                modifier = Modifier.testTag(ConstansUiIdentifiers.MARVEL_CARD_LIST),
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                hero = MarvelHeroCompModel(
                    name = hero.name,
                    faction = hero.factionCode,
                    imageUrl = hero.imagesrc,
                    isFavourite = hero.isFavourite.value
                )
            ) {
                navigationDetailAction(hero.code)
            }
        }
    }

}