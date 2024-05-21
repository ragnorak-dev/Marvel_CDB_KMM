package com.ragnorak.marvelcdb.di

import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.stubFiles.StubMarvelRepository
import org.koin.dsl.module

val stubCommonModule = module {
    single <MarvelCardRepository>{ StubMarvelRepository() }
}