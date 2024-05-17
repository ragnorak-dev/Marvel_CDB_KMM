package com.ragnorak.marvelcdb.ui.heroDetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MarvelHeroDetails(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    model: MarvelCardModel
) {
    val scrollState = rememberScrollState()
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image-${model.name}"),
                        animatedVisibilityScope,
                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                model = model.imagesrc,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = model.name,
                style = MaterialTheme.typography.titleLarge)
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.Start) {
                Text(text = "Attack: ${model.attack}",
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = "defense: ${model.defense}",
                    style = MaterialTheme.typography.bodyMedium)
                Text(text = "health: ${model.health}",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
