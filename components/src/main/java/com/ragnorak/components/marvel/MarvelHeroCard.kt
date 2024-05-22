package com.ragnorak.components.marvel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.ragnorak.components.foundations.Dimensions

data class MarvelHeroCompModel(
    val name: String,
    val faction: String,
    val imageUrl: String?,
    val isFavourite: Boolean,
    val favouriteIcon: ImageVector? = null
)

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
                .padding(Dimensions.s)
                .clickable {
                    onclick()
                },
            elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.xs)
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
                        .clip(MaterialTheme.shapes.medium),
                    model = hero.imageUrl,
                    contentDescription = hero.name,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = Dimensions.s),
                    maxLines = 2,
                    text = hero.name,
                    style = MaterialTheme.typography.titleSmall
                )

                hero.favouriteIcon?.let {
                    Icon(
                        modifier = Modifier
                            .size(Dimensions.md)
                            .align(Alignment.CenterVertically),
                        imageVector = hero.favouriteIcon,
                        contentDescription = null,
                        tint = if (hero.isFavourite) {
                            Color.Yellow
                        } else {
                            Color.Gray
                        }
                    )
                }
            }
        }


    }
}