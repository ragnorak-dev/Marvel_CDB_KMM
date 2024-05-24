package com.ragnorak.marvelcdb.data

import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

val fakeException = Exception("Something went wrong")

val fakeMarvelHeroesModel = listOf(
    MarvelCardModel(code = "1011334", typeCode = "hero", name = "Iron Man", traits = "Avengers", isFavourite = true),
    MarvelCardModel(code = "1011335", typeCode = "hero", name = "Captain America", traits = "Avengers"),
    MarvelCardModel(code = "1011336", typeCode = "hero", name = "Thor", traits = "Avengers", isFavourite = true),
    MarvelCardModel(code = "1011337", typeCode = "hero", name = "Wolverine", traits = "X-men")
)

val fakeMarvelFavouritesHeroesModel = listOf(
    MarvelCardModel(code = "1011334", typeCode = "hero", name = "Iron Man", traits = "Avengers"),
    MarvelCardModel(code = "1011336", typeCode = "hero", name = "Thor", traits = "Avengers"),
)