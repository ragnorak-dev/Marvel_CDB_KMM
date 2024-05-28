package com.ragnorak.components.marvel

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ragnorak.components.foundations.Dimensions
import com.ragnorak.components.foundations.ShimmerEffect


@Composable
fun MarvelHeroLoadingCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(Dimensions.s),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.xs)
    ) {
        Column(
            modifier = Modifier
                .height(Dimensions.cardHeight)
                .width(Dimensions.cardWidth)
                .clip(MaterialTheme.shapes.medium)
                .background(ShimmerEffect())

        ) {

        }


        Row(
            modifier = Modifier
                .padding(Dimensions.s)
                .height(Dimensions.lg)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(brush = ShimmerEffect()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

        }
    }
}