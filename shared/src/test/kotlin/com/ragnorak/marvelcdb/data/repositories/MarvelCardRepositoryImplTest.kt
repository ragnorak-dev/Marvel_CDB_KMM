package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.fakeData.exception
import com.ragnorak.marvelcdb.fakeData.marvelHeroesResponse
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class MarvelCardRepositoryImplTest{

    private val dataSource = mock<MarvelCardsDataSource>()

    private val sut = MarvelCardRepositoryImpl(dataSource)

    @Test
    fun `repository returns marvel heroes success`() = runBlocking {

        everySuspend {
            dataSource.getMarvelCardList()
        } returns Result.success(marvelHeroesResponse)

        val result = sut.getMarvelCardList()

        assertEquals(marvelHeroesResponse, result.getOrNull())
    }

    @Test
    fun `repository returns marvel heroes Error`() = runBlocking {

        everySuspend {
            dataSource.getMarvelCardList()
        } returns Result.failure(exception)

        val result = sut.getMarvelCardList()

        assertEquals(exception, result.exceptionOrNull())
    }
}