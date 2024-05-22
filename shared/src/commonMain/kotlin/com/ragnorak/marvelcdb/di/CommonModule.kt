package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.datasource.MarvelCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.MarvelFavouriteCardsDataSource
import com.ragnorak.marvelcdb.data.datasource.cloud.MarvelCardsCloudDataSourceImpl
import com.ragnorak.marvelcdb.data.datasource.local.MarvelCardsLocalDataSourceImpl
import com.ragnorak.marvelcdb.data.network.ApiClient
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepositoryImpl
import com.ragnorak.marvelcdb.domain.AddMarvelFavouriteCardUseCaseImpl
import com.ragnorak.marvelcdb.domain.DeleteMarvelFavouriteCardUseCaseImpl
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.GetMarvelCardAvengerListUseCaseImpl
import com.ragnorak.marvelcdb.domain.GetMarvelCardFavoriteListUseCaseImpl
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.getApiEngine
import org.koin.dsl.module

val commonModule = module {
    single { ApiClient(getApiEngine()) }
    single <MarvelCardsDataSource> { MarvelCardsCloudDataSourceImpl(get()) }
    single <MarvelFavouriteCardsDataSource> { MarvelCardsLocalDataSourceImpl(get()) }
    single <MarvelCardRepository> { MarvelCardRepositoryImpl(get(), get()) }
    single <AddMarvelFavouriteCardUseCase> { AddMarvelFavouriteCardUseCaseImpl(get()) }
    single <DeleteMarvelFavouriteCardUseCase> { DeleteMarvelFavouriteCardUseCaseImpl(get()) }
    single <GetMarvelCardFavoriteListUseCase> { GetMarvelCardFavoriteListUseCaseImpl(get()) }
    single <GetMarvelCardListUseCase> { GetMarvelCardAvengerListUseCaseImpl(get(), get()) }
}