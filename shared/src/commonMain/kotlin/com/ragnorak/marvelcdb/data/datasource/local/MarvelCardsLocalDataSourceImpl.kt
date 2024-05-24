package com.ragnorak.marvelcdb.data.datasource.local

import com.ragnorak.marvelcdb.data.datasource.MarvelFavouriteCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDao
import kotlinx.coroutines.flow.Flow

class MarvelCardsLocalDataSourceImpl(
    private val marvelCardsDao: MarvelCardDao
) : MarvelFavouriteCardsDataSource {

    override fun getMarvelCardList(): Flow<List<MarvelFavouriteCardEntity>> =
        marvelCardsDao.getAll()

    override suspend fun addFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) =
        marvelCardsDao.upsert(marvelFavouriteCardEntity)

    override suspend fun deleteFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) =
        marvelCardsDao.delete(marvelFavouriteCardEntity)

    override suspend fun deleteAll() {
        marvelCardsDao.deleteAll()
    }
}