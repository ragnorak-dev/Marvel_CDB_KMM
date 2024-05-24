package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.MarvelFavouriteCardsDataSource
import com.ragnorak.marvelcdb.fakeData.exception
import com.ragnorak.marvelcdb.fakeData.marvelHeroesResponse
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class MarvelCardRepositoryImplTest{

    private val cloudDataSource = mock<MarvelCardsDataSource>()
    private val localDataSource = mock<MarvelFavouriteCardsDataSource>()

    private val sut = MarvelCardRepositoryImpl(
        marvelCardsCloudDataSource = cloudDataSource,
        marvelCardsLocalDataSource = localDataSource
    )

    @Test
    fun `repository returns marvel heroes success`() = runBlocking {

        everySuspend {
            cloudDataSource.getMarvelCardList()
        } returns Result.success(marvelHeroesResponse)

        val result = sut.getMarvelCardList()

        assertEquals(marvelHeroesResponse, result.getOrNull())
    }

    @Test
    fun `repository returns marvel heroes Error`() = runBlocking {

        everySuspend {
            cloudDataSource.getMarvelCardList()
        } returns Result.failure(exception)

        val result = sut.getMarvelCardList()

        assertEquals(exception, result.exceptionOrNull())
    }
}