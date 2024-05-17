package com.ragnorak.components.marvel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MarvelHeroCompModel(val name: String, val faction: String, val imageUrl: String)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelHeroCard(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    hero: MarvelHeroCompModel,
    onclick: () -> Unit
) {
    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onclick()
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(
                                key = "image-${hero.name}",
                            ),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    model = hero.imageUrl,
                    contentDescription = hero.name,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
            }

    }
}