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
    MarvelCardResponse(typeCode = "hero", name = "Iron Man", traits = "Avengers", imagesrc = "/ironman.jpg"),
    MarvelCardResponse(typeCode = "hero", name = "Captain America", traits = "Avengers"),
    MarvelCardResponse(typeCode = "hero", name = "Thor", traits = "Avengers"),
    MarvelCardResponse(typeCode = "hero", name = "Wolverine", traits = "X-men")
)

val expectedMarvelHeroesModel = listOf(
    MarvelCardModel(typeCode = "hero", name = "Iron Man", traits = "Avengers", imagesrc = "https://marvelcdb.com/ironman.jpg"),
    MarvelCardModel(typeCode = "hero",  name = "Captain America", traits = "Avengers"),
    MarvelCardModel(typeCode = "hero", name = "Thor", traits = "Avengers")
)

const val marvelJsonResponse =  """
                    [
                        {"type_code": "hero", "name": "Iron Man", "traits": "Avengers", "imagesrc": "/ironman.jpg"},
                        {"type_code": "hero", "name": "Captain America", "traits": "Avengers"},
                        {"type_code": "hero", "name": "Thor", "traits": "Avengers"},
                        {"type_code": "hero", "name": "Wolverine", "traits": "X-men"}
                    ]
                """