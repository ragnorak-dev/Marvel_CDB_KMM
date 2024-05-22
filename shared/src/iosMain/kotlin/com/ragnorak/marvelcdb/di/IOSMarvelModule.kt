package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDao
import com.ragnorak.marvelcdb.getFavouriteMarvelCards
import org.koin.dsl.module

val iOSMarvelModule = module {
    single <MarvelCardDao> { getFavouriteMarvelCards().marvelCardDao() }
}