package com.ragnorak.marvelcdb.ui

import androidx.compose.runtime.snapshots.SnapshotStateList
import app.cash.turbine.test
import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.fakeException
import com.ragnorak.marvelcdb.data.fakeMarvelFavouritesHeroesModel
import com.ragnorak.marvelcdb.data.fakeMarvelHeroesModel
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
class MarvelCardListErrorViewModelTest {

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
        } returns ResultData.failure(
            fakeException
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
            assertEquals(fakeException.message, (awaitItem() as ViewState.Error).errorMessage)
            cancelAndIgnoreRemainingEvents()
        }
    }
}