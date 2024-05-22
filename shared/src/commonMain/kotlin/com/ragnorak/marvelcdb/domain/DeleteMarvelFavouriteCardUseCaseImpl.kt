package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

class DeleteMarvelFavouriteCardUseCaseImpl(private val marvelRepository: MarvelCardRepository) : DeleteMarvelFavouriteCardUseCase {
    override suspend fun invoke(marvelCardModel: MarvelCardModel) =
        marvelRepository.deleteFavouriteCard(marvelCardModel.toEntity())
}