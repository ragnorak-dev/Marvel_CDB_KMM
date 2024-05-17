package com.ragnorak.marvelcdb

import com.ragnorak.marvelcdb.di.commonModule
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCaseImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GetMarvelCardListUseCaseHelper : KoinComponent {
    private val getMarvelCardListUseCase: GetMarvelCardListUseCaseImpl by inject()
    fun getMarvelCardListUseCase(): GetMarvelCardListUseCaseImpl = getMarvelCardListUseCase
}

fun initKoin() {
    startKoin {
        modules(commonModule)
    }
}
