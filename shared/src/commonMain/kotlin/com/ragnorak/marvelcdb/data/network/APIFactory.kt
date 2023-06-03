package com.ragnorak.marvelcdb.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

internal class APIFactory {

    private val BASE_URL = "https://marvelcdb.com/api/public/"
    private val httpClient = HttpClient()

    suspend fun createApi(url: String): Result<String> {
        try {
            return Result.success(httpClient.get(BASE_URL.plus(url)).bodyAsText())
        } catch (exception: Throwable) {
            return Result.failure(exception)
        }
    }
}

