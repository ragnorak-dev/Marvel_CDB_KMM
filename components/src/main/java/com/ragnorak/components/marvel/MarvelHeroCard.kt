package com.ragnorak.components.marvel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MarvelHeroCompModel(val name: String, val faction: String, val imageUrl: String?)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelHeroCard(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    hero: MarvelHeroCompModel,
    onclick: () -> Unit
) {
    with(sharedTransitionScope) {
        Card(
            modifier = modifier
                .padding(8.dp)
                .clickable {
                    onclick()
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            hero.imageUrl?.let {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(
                                key = "image-${hero.imageUrl}",
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

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    maxLines = 2,
                    text = hero.name,
                    style = MaterialTheme.typography.titleSmall
                )
            }


    }
}