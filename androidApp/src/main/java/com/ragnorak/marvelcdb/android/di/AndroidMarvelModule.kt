package com.ragnorak.marvelcdb.android.di

import com.ragnorak.marvelcdb.android.ui.MarvelCardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { MarvelCardListViewModel(get())}
}