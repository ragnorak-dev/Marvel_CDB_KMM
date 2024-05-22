package com.ragnorak.marvelcdb.domain.interfaces

import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import kotlinx.coroutines.flow.Flow

interface GetMarvelCardFavoriteListUseCase {
    operator fun invoke(): Flow<List<MarvelCardModel>>
}