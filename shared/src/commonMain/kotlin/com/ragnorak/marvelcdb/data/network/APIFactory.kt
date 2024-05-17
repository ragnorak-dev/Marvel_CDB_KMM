package com.ragnorak.marvelcdb.data.network

import com.ragnorak.marvelcdb.HOST_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class APIFactory {

    private val BASE_URL = "$HOST_URL/api/public/"
    private val httpClient = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            // Example: Register JSON content transformation
            // Add more transformations as needed for other content types
        }
    }

    suspend inline fun <reified T> createApi(url: String): Result<T> {
        try {
            return Result.success(
                httpClient
                    .get(BASE_URL.plus(url)){
                        this.header(HttpHeaders.ContentType, "application/json")
                        this.header(HttpHeaders.Accept, "application/json")
                    }
                .body<T>())
        } catch (exception: Throwable) {
            return Result.failure(exception)
        }
    }
}

