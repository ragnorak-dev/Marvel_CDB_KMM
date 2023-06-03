package com.ragnorak.marvelcdb.data.repositories

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse

interface MarvelCardRepository {

    suspend fun getMarvelCardList(): Result<List<MarvelCardResponse>>
}