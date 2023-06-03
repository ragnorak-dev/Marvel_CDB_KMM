package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.datasource.cloud.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.network.APIFactory
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepositoryImpl
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCase
import org.koin.dsl.module

val commonModule = module {
    single { APIFactory() }
    single { MarvelCardsDataSource(get()) }
    single <MarvelCardRepository>{ MarvelCardRepositoryImpl(get()) }
    single { GetMarvelCardListUseCase(get()) }
}