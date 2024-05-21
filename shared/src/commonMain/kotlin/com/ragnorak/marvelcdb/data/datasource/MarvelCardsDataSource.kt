package com.ragnorak.marvelcdb.data.datasource

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse

internal interface MarvelCardsDataSource {
    suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>>
}