package com.ragnorak.marvelcdb.data

import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

val marvelHeroesResponse = listOf(
    MarvelCardResponse(code = "1011334", typeCode = "hero", name = "Iron Man", traits = "Avengers"),
    MarvelCardResponse(
        code = "1011335",
        typeCode = "hero",
        name = "Captain America",
        traits = "Avengers"
    ),
    MarvelCardResponse(code = "1011336", typeCode = "hero", name = "Thor", traits = "Avengers"),
    MarvelCardResponse(code = "1011337", typeCode = "hero", name = "Wolverine", traits = "X-men")
)

val fakeMarvelHeroesModel = listOf(
    MarvelCardModel(code = "1011334", typeCode = "hero", name = "Iron Man", traits = "Avengers"),
    MarvelCardModel(code = "1011335", typeCode = "hero", name = "Captain America", traits = "Avengers"),
    MarvelCardModel(code = "1011336", typeCode = "hero", name = "Thor", traits = "Avengers"),
    MarvelCardModel(code = "1011337", typeCode = "hero", name = "Wolverine", traits = "X-men")
)

val fakeFavouritesModel = listOf(
    MarvelCardModel(code = "1011334", name = "Iron Man", traits = "Avengers", imagesrc = "", isFavourite = true),
    MarvelCardModel(code = "1011336", name = "Thor", traits = "Avengers", imagesrc = "", isFavourite = true),
)

val fakeFavourites = listOf(
    MarvelFavouriteCardEntity(code = "1011334", name = "Iron Man", traits = "Avengers"),
    MarvelFavouriteCardEntity(code = "1011336", name = "Thor", traits = "Avengers"),
)