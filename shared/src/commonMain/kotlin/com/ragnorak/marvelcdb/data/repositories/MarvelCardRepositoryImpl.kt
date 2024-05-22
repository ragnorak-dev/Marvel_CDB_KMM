package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.MarvelFavouriteCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import kotlinx.coroutines.flow.Flow

internal class MarvelCardRepositoryImpl(
    private val marvelCardsCloudDataSource: MarvelCardsDataSource,
    private val marvelCardsLocalDataSource: MarvelFavouriteCardsDataSource
) : MarvelCardRepository {

    override suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        marvelCardsCloudDataSource.getMarvelCardList()

    override fun getMarvelCardFavoriteList(): Flow<List<MarvelFavouriteCardEntity>> =
        marvelCardsLocalDataSource.getMarvelCardList()

    override suspend fun addFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) =
        marvelCardsLocalDataSource.addFavouriteCard(marvelFavouriteCardEntity)

    override suspend fun deleteFavouriteCard(marvelFavouriteCardEntity: MarvelFavouriteCardEntity) =
        marvelCardsLocalDataSource.deleteFavouriteCard(marvelFavouriteCardEntity)
}