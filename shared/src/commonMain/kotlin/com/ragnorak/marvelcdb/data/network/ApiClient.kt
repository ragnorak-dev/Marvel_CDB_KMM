package com.ragnorak.marvelcdb.data.network

import com.ragnorak.marvelcdb.HOST_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class ApiClient(engine: HttpClientEngine) {

    private val BASE_URL = "$HOST_URL/api/public/"
    private val httpClient = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend inline fun <reified T> getApiCall(url: String): Result<T> =
        try {
            val response = httpClient.get(BASE_URL.plus(url)) {
                this.header(HttpHeaders.ContentType, "application/json")
                this.header(HttpHeaders.Accept, "application/json")
            }
            Result.success(response.body<T>())
        } catch (exception: ResponseException) {
            Result.failure(exception)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
}

