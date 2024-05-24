package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.domain.models.mappers.toModel
import kotlinx.coroutines.flow.first

class GetMarvelCardAvengerListUseCaseImpl(
    private val marvelCardRepository: MarvelCardRepository,
    private val getMarvelCardFavouritesUseCase: GetMarvelCardFavoriteListUseCase
) : GetMarvelCardListUseCase {

    override suspend operator fun invoke(): ResultData<List<MarvelCardModel>> {
        val favourites = getMarvelCardFavouritesUseCase().first()
        val result = marvelCardRepository.getMarvelCardList()

        return result.fold(
            onSuccess = {
                val marvelCardList = it.filter { hero ->
                    hero.traits.contains("Avenger")
                }.filter { hero ->
                    hero.typeCode == "hero"
                }.map { hero ->
                    hero.toModel()
                }
                ResultData.success(marvelCardList)
            },
            onFailure =
            { ResultData.failure(it) })
    }
}