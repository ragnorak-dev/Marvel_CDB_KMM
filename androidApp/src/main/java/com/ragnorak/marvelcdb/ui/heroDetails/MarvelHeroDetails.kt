package com.ragnorak.marvelcdb.ui.heroDetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ragnorak.marvelcdb.android.R
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.ui.ConstansUiIdentifiers
import com.ragnorak.components.foundations.Dimensions

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelHeroDetails(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    model: MarvelCardModel,
    addFavouriteAction: (MarvelCardModel) -> Unit,
    deleteFavouriteAction: (MarvelCardModel) -> Unit
) {
    val scrollState = rememberScrollState()
    var favouriteState by remember{ mutableStateOf(model.isFavourite)}
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .testTag(ConstansUiIdentifiers.MARVEL_CARD_DETAILS)
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            model.imagesrc?.let {
                AsyncImage(
                    modifier = Modifier
                        .testTag(ConstansUiIdentifiers.MARVEL_CARD_DETAILS_IMAGE)
                        .sharedElement(
                            rememberSharedContentState(key = "image-${model.imagesrc}"),
                            animatedVisibilityScope,
                        )
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimensions.md)),
                    model = model.imagesrc,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(Dimensions.md))

            Text(
                modifier = Modifier
                    .testTag(ConstansUiIdentifiers.MARVEL_CARD_DETAILS_NAME),
                text = model.name,
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .testTag(ConstansUiIdentifiers.MARVEL_CARD_DETAILS_INFO)
                        .padding(Dimensions.md),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${stringResource(id = R.string.attack)}: ${model.attack}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${stringResource(id = R.string.defense)}: ${model.defense}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "${stringResource(id = R.string.health)}: ${model.health}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Icon(
                    modifier = Modifier
                        .size(Dimensions.xxxl)
                        .clickable {
                            if (model.isFavourite) {
                                deleteFavouriteAction(model)
                            } else {
                                addFavouriteAction(model)
                            }
                            favouriteState = !favouriteState
                        }
                        .align(Alignment.CenterVertically),
                    imageVector = ImageVector.vectorResource(id = R.drawable.favorite_icon),
                    contentDescription = null,
                    tint = if (favouriteState) {
                        Color.Yellow
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}
