package com.ragnorak.marvelcdb

import com.ragnorak.marvelcdb.di.commonModule
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GetMarvelCardListUseCaseHelper : KoinComponent {
    private val getMarvelCardListUseCase: GetMarvelCardListUseCase by inject()
    fun getMarvelCardListUseCase(): GetMarvelCardListUseCase = getMarvelCardListUseCase
}

fun initKoin() {
    startKoin {
        modules(commonModule)
    }
}
