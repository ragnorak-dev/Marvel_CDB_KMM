package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.fakeData.exception
import com.ragnorak.marvelcdb.fakeData.expectedMarvelHeroesModel
import com.ragnorak.marvelcdb.fakeData.marvelHeroesResponse
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetMarvelCardAvengerListUseCaseImplTest {

    private val repository = mock<MarvelCardRepository>()
    private val getFavoriteUseCase = mock<GetMarvelCardFavoriteListUseCase>()

    private val sut = GetMarvelCardAvengerListUseCaseImpl(
        marvelCardRepository = repository,
        getMarvelCardFavouritesUseCase = getFavoriteUseCase
    )

    @Test
    fun `Use case returns marvel heroes success`() = runBlocking {

        everySuspend {
            repository.getMarvelCardList()
        } returns Result.success(marvelHeroesResponse)

        everySuspend {
            getFavoriteUseCase()
        } returns MutableStateFlow(emptyList())

        val result = sut()

        assertEquals(expectedMarvelHeroesModel, result.value)
    }

    @Test
    fun `Use case returns marvel heroes Error`() = runBlocking {

        everySuspend {
            repository.getMarvelCardList()
        } returns Result.failure(exception)

        everySuspend {
            getFavoriteUseCase()
        } returns MutableStateFlow(emptyList())

        val result = sut()

        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `Use case returns a custom ResultData class type`() = runBlocking {

        everySuspend {
            repository.getMarvelCardList()
        } returns Result.failure(exception)

        everySuspend {
            getFavoriteUseCase()
        } returns MutableStateFlow(emptyList())

        val result = sut()

        assert(result is ResultData)
    }
}