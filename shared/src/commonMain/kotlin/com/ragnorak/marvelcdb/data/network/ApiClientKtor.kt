package com.ragnorak.marvelcdb.data.network

import com.ragnorak.marvelcdb.HOST_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class ApiClientKtor {

    private val BASE_URL = "$HOST_URL/api/public/"
    private val httpClient = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend inline fun <reified T> getApiCall(url: String): Result<T> {
        return try {
            Result.success(
                httpClient
                    .get(BASE_URL.plus(url)){
                        this.header(HttpHeaders.ContentType, "application/json")
                        this.header(HttpHeaders.Accept, "application/json")
                    }
                    .body<T>())
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}

