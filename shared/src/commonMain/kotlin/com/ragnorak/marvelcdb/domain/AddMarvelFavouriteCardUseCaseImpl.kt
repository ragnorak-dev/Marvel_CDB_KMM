package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

class AddMarvelFavouriteCardUseCaseImpl(private val marvelRepository: MarvelCardRepository) : AddMarvelFavouriteCardUseCase {
    override suspend fun invoke(marvelCardModel: MarvelCardModel) {
        marvelRepository.addFavouriteCard(marvelCardModel.toEntity())
    }
}