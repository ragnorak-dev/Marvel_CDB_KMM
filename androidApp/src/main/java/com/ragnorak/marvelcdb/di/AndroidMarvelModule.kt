package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDao
import com.ragnorak.marvelcdb.database.getFavouriteMarvelCards
import com.ragnorak.marvelcdb.ui.MarvelCardListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single <MarvelCardDao> { getFavouriteMarvelCards(context = androidContext()).marvelCardDao() }
    viewModel { MarvelCardListViewModel(get(), get(), get(), get()) }
}