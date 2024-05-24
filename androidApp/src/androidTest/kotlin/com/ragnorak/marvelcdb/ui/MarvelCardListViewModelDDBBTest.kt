package com.ragnorak.marvelcdb.ui

import app.cash.turbine.test
import com.ragnorak.marvelcdb.data.datasource.MarvelFavouriteCardsDataSource
import com.ragnorak.marvelcdb.data.fakeFavouritesModel
import com.ragnorak.marvelcdb.data.fakeMarvelHeroesModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.koin.core.component.inject
import org.koin.test.KoinTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MarvelCardListViewModelDDBBTest : KoinTest {

    private val marvelFavouriteCardsDataSource: MarvelFavouriteCardsDataSource by inject<MarvelFavouriteCardsDataSource>()
    private val sut: MarvelCardListViewModel by inject<MarvelCardListViewModel>()

    @Before
    fun setUp() {
        runTest {
            marvelFavouriteCardsDataSource.deleteAll()
        }
    }

    @Test
    fun marvelHeroes_add_favourite_hero() = runTest {
        sut.marvelCardFavoriteList.test {
            sut.addMarvelFavouriteCard(fakeMarvelHeroesModel.first())
            assertEquals(0, awaitItem().size)
            assertEquals(fakeFavouritesModel.first(), awaitItem().first())
        }
    }

    @Test
    fun marvelHeroes_delete_favourite_hero() = runTest {
        sut.marvelCardFavoriteList.test {
            sut.addMarvelFavouriteCard(fakeMarvelHeroesModel.first())
            assertEquals(0, awaitItem().size)
            assertEquals(fakeFavouritesModel.first(), awaitItem().first())
            sut.deleteMarvelFavouriteCard(fakeMarvelHeroesModel.first())
            assertEquals(0, awaitItem().size)
        }
    }
}