package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.cloud.MarvelCardsCloudDataSourceImpl
import com.ragnorak.marvelcdb.data.network.ApiClient
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepositoryImpl
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCaseImpl
import com.ragnorak.marvelcdb.getApiEngine
import org.koin.dsl.module

val commonModule = module {
    single { ApiClient(getApiEngine()) }
    single<MarvelCardsDataSource> { MarvelCardsCloudDataSourceImpl(get()) }
    single <MarvelCardRepository>{ MarvelCardRepositoryImpl(get()) }
    single <GetMarvelCardListUseCase> { GetMarvelCardListUseCaseImpl(get()) }
}