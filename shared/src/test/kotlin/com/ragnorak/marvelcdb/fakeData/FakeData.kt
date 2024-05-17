package com.ragnorak.marvelcdb.fakeData

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

val exception = Exception("Something went wrong")

val marvelHeroesResponse = listOf(
    MarvelCardResponse(name = "Iron Man"),
    MarvelCardResponse(name = "Captain America"),
    MarvelCardResponse(name = "Thor")
)

val expectedMarvelHeroes = listOf(
    MarvelCardModel(name = "Iron Man", imagesrc = "https://marvelcdb.com"),
    MarvelCardModel(name = "Captain America", imagesrc = "https://marvelcdb.com"),
    MarvelCardModel(name = "Thor", imagesrc = "https://marvelcdb.com")
)
