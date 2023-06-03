package com.ragnorak.marvelcdb.domain

import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.domain.models.mappers.toModel

class GetMarvelCardListUseCase(private val marvelCardRepository: MarvelCardRepository) {

    suspend fun execute(): ResultData<List<MarvelCardModel>> {
        val result = marvelCardRepository.getMarvelCardList()

        result.onSuccess {  }
        return result.fold(
            onSuccess = { ResultData.success(it.map { it.toModel() }) },
            onFailure = { ResultData.failure(it) })
    }
}