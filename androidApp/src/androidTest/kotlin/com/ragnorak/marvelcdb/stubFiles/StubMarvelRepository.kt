package com.ragnorak.marvelcdb.stubFiles

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.data.marvelHeroesResponse
import com.ragnorak.marvelcdb.data.repositories.MarvelCardRepository

class StubMarvelRepository : MarvelCardRepository {
    override suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>> =
        Result.success(marvelHeroesResponse)

}