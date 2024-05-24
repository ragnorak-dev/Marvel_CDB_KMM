package com.ragnorak.marvelcdb.ui

import app.cash.turbine.test
import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.fakeMarvelFavouritesHeroesModel
import com.ragnorak.marvelcdb.data.fakeMarvelHeroesModel
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MarvelCardListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val getMarvelCardListUseCase = mock<GetMarvelCardListUseCase>()
    private val getMarvelCardFavoriteListUseCase = mock<GetMarvelCardFavoriteListUseCase>()
    private val addMarvelFavouriteCardUseCase = mock<AddMarvelFavouriteCardUseCase>()
    private val deleteMarvelFavouriteCardUseCase = mock<DeleteMarvelFavouriteCardUseCase>()
    private lateinit var sut: MarvelCardListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        everySuspend {
            getMarvelCardListUseCase()
        } returns ResultData.success(
            fakeMarvelHeroesModel
        )

        everySuspend {
            getMarvelCardFavoriteListUseCase()
        } returns flow { emit(fakeMarvelFavouritesHeroesModel) }

        sut = MarvelCardListViewModel(
            marvelCardListUseCase = getMarvelCardListUseCase,
            getMarvelCardFavoriteListUseCase = getMarvelCardFavoriteListUseCase,
            addMarvelFavouriteCardUseCase = addMarvelFavouriteCardUseCase,
            deleteMarvelFavouriteCardUseCase = deleteMarvelFavouriteCardUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMarvelCardList() should update state with list of MarvelHero`() = runTest {

        sut.marvelCardList.test {
            sut.getMarvelCardList()
            assertTrue(awaitItem() is ViewState.Idle)
            assertTrue(awaitItem() is ViewState.Loading)
            assertEquals(fakeMarvelHeroesModel, (awaitItem() as ViewState.Success).data)
            cancelAndIgnoreRemainingEvents()
        }
    }
}