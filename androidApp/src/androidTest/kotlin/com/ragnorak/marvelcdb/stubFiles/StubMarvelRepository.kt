package com.ragnorak.marvelcdb.stubFiles

import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.data.fakeFavourites
import com.ragnorak.marvelcdb.data.marvelHeroesResponse
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StubMarvelRepository : MarvelCardRepository {
    override suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        Result.success(marvelHeroesResponse)

    override fun getMarvelCardFavoriteList(): Flow<List<MarvelFavouriteCardEntity>> = flow {
        emit(fakeFavourites)
    }

    override suspend fun addFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) {}

    override suspend fun deleteFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) {}

}