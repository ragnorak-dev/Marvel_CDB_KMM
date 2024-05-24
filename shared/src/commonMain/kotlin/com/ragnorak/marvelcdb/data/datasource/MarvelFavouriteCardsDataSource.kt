package com.ragnorak.marvelcdb.data.datasource

import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import kotlinx.coroutines.flow.Flow

interface MarvelFavouriteCardsDataSource {
    fun getMarvelCardList(): Flow<List<MarvelFavouriteCardEntity>>
    suspend fun addFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity)
    suspend fun deleteFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity)
    suspend fun deleteAll()
}