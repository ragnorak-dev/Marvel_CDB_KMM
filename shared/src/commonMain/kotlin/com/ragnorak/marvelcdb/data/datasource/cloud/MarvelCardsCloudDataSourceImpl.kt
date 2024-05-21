package com.ragnorak.marvelcdb.data.datasource.cloud

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.data.network.ApiClient

internal class MarvelCardsCloudDataSourceImpl(
    private val apiClient: ApiClient
) : MarvelCardsDataSource {
    private val endpoint = "cards/?_format=json"
    override suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        apiClient.getApiCall<List<MarvelCardResponse>>(endpoint)
}