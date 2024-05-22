package com.ragnorak.marvelcdb.domain.interfaces

import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

interface DeleteMarvelFavouriteCardUseCase {
    suspend operator fun invoke(marvelCardModel: MarvelCardModel)
}