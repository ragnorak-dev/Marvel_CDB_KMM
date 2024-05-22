package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import kotlinx.coroutines.flow.Flow

interface MarvelCardRepository {

    suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>>
    fun getMarvelCardFavoriteList(): Flow<List<MarvelFavouriteCardEntity>>
    suspend fun addFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity)
    suspend fun deleteFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity)
}