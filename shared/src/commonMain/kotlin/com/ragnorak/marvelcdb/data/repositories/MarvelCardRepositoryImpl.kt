package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse

internal class MarvelCardRepositoryImpl(
    private val marvelCardsCloudDataSource: MarvelCardsDataSource
) : MarvelCardRepository {

    override suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        marvelCardsCloudDataSource.getMarvelCardList()
}