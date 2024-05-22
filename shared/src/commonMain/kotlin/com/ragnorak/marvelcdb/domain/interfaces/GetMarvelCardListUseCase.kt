package com.ragnorak.marvelcdb.domain.interfaces

import com.ragnorak.marvelcdb.ResultData
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

interface GetMarvelCardListUseCase {
    suspend operator fun invoke(): ResultData<List<MarvelCardModel>>
}