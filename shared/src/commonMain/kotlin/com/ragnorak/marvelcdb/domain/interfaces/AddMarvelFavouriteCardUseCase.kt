package com.ragnorak.marvelcdb.domain.interfaces

import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

interface AddMarvelFavouriteCardUseCase {
    suspend operator fun invoke(marvelCardModel: MarvelCardModel)
}