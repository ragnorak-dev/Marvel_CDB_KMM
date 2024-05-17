package com.ragnorak.marvelcdb.data.datasource.cloud

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.data.network.ApiClient

internal class MarvelCardsDataSource(private val apiClient: ApiClient) {
    private val endpoint = "cards/?_format=json"
    suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        apiClient.getApiCall<List<MarvelCardResponse>>(endpoint)
}