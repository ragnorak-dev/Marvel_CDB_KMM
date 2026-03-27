package com.ragnorak.marvelcdb

import com.ragnorak.marvelcdb.di.commonModule
import com.ragnorak.marvelcdb.di.iOSMarvelModule
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class MarvelUseCaseProvider : KoinComponent {
    val marvelCardListUseCase: GetMarvelCardListUseCase by inject()
    val favoriteListUseCase: GetMarvelCardFavoriteListUseCase by inject()
    val addFavoriteUseCase: AddMarvelFavouriteCardUseCase by inject()
    val deleteFavoriteUseCase: DeleteMarvelFavouriteCardUseCase by inject()

    // Concrete property so SKIE wraps Flow<List<MarvelCardModel>> as SkieSwiftFlow
    val favoriteFlow: Flow<List<MarvelCardModel>> get() = favoriteListUseCase.invoke()
}

fun initKoin() {
    startKoin {
        modules(iOSMarvelModule, commonModule)
    }
}
