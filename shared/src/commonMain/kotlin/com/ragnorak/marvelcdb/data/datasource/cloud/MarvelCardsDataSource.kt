package com.ragnorak.marvelcdb.data.datasource.cloud

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.data.network.APIFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class MarvelCardsDataSource(private val apiFactory: APIFactory) {
    private val endpoint = "cards/?_format=json"
    suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        apiFactory.createApi(endpoint).map {

            Json{ ignoreUnknownKeys = true }
                .decodeFromString(it)

        }
}