package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.ui.MarvelCardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { MarvelCardListViewModel(get()) }
}