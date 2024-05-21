package com.ragnorak.marvelcdb.fakeData

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

val exception = Exception("Something went wrong")

val mockEngine = MockEngine {
    respond(
        content = marvelJsonResponse.trimIndent(),
        status = HttpStatusCode.OK,
        headers = headersOf("Content-Type", "application/json")
    )
}

val mockErrorEngine = MockEngine {
    respond(
        content = exception.message ?: "",
        status = HttpStatusCode.InternalServerError,
    )
}


val marvelHeroesResponse = listOf(
    MarvelCardResponse(name = "Iron Man", factionCode = "Avengers"),
    MarvelCardResponse(name = "Captain America", factionCode = "Avengers"),
    MarvelCardResponse(name = "Thor", factionCode = "Avengers")
)

val expectedMarvelHeroesModel = listOf(
    MarvelCardModel(name = "Iron Man", factionCode = "Avengers", imagesrc = "https://marvelcdb.com"),
    MarvelCardModel(name = "Captain America", factionCode = "Avengers", imagesrc = "https://marvelcdb.com"),
    MarvelCardModel(name = "Thor", factionCode = "Avengers", imagesrc = "https://marvelcdb.com")
)


const val marvelJsonResponse =  """
                    [
                        {"name": "Iron Man", "faction_code": "Avengers"},
                        {"name": "Captain America", "faction_code": "Avengers"},
                        {"name": "Thor", "faction_code": "Avengers"}
                    ]
                """