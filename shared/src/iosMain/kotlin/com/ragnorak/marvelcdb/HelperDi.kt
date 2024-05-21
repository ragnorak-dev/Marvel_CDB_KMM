package com.ragnorak.marvelcdb

import com.ragnorak.marvelcdb.di.commonModule
import com.ragnorak.marvelcdb.domain.GetMarvelCardAvengerListUseCaseImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class GetMarvelCardListUseCaseHelper : KoinComponent {
    private val getMarvelCardListUseCase: GetMarvelCardAvengerListUseCaseImpl by inject()
    fun getMarvelCardListUseCase(): GetMarvelCardAvengerListUseCaseImpl = getMarvelCardListUseCase
}

fun initKoin() {
    startKoin {
        modules(commonModule)
    }
}
