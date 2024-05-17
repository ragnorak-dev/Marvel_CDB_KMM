package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.domain.models.mappers.toModel

class GetMarvelCardListUseCaseImpl(
    private val marvelCardRepository: MarvelCardRepository
) : GetMarvelCardListUseCase {

    override suspend operator fun invoke(): ResultData<List<MarvelCardModel>> {
        val result = marvelCardRepository.getMarvelCardList()

        return result.fold(
            onSuccess = { ResultData.success(it.map { it.toModel() }) },
            onFailure = { ResultData.failure(it) })
    }
}