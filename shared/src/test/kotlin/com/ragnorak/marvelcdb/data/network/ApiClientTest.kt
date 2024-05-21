package com.ragnorak.marvelcdb.data.network

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.fakeData.marvelHeroesResponse
import com.ragnorak.marvelcdb.fakeData.mockEngine
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlin.test.Test


class ApiClientTest {

    @Test
    fun `Api client should return list of MarvelHero`() = runBlocking {

        val apiClient = ApiClient(mockEngine)

        val result = apiClient.getApiCall<List<MarvelCardResponse>>("/")

        assertEquals(marvelHeroesResponse, result.getOrNull())
    }
}