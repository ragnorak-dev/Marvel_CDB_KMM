package com.ragnorak.marvelcdb.data.datasource.cloud

import com.ragnorak.marvelcdb.data.network.ApiClient
import com.ragnorak.marvelcdb.fakeData.marvelHeroesResponse
import com.ragnorak.marvelcdb.fakeData.mockEngine
import com.ragnorak.marvelcdb.fakeData.mockErrorEngine
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MarvelCardsCloudDataSourceImplTest {

    @Test
    fun `Data source getMarvelHeroList() should return list of MarvelHero success`() = runBlocking {

        val apiClient = ApiClient(mockEngine)
         val sut = MarvelCardsCloudDataSourceImpl(apiClient)

        val result = sut.getMarvelCardList()

        assertEquals(marvelHeroesResponse, result.getOrNull())
    }

    @Test
    fun `Data source getMarvelHeroList() should return an error`() = runBlocking {

        val apiClient = ApiClient(mockErrorEngine)
        val sut = MarvelCardsCloudDataSourceImpl(apiClient)

        val result = sut.getMarvelCardList()

        assertTrue(result.exceptionOrNull() is Exception)
    }
}