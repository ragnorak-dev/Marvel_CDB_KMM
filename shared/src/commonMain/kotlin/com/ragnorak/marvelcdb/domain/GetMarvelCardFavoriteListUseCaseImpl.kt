package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMarvelCardFavoriteListUseCaseImpl(
    private val repository: MarvelCardRepository
) : GetMarvelCardFavoriteListUseCase {
    override fun invoke(): Flow<List<MarvelCardModel>> =
        repository.getMarvelCardFavoriteList()
            .map { it.map { marvelCardEntity -> marvelCardEntity.toModel() } }


}