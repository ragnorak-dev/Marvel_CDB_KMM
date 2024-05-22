package com.ragnorak.marvelcdb

import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDao
import io.ktor.client.engine.HttpClientEngine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getApiEngine(): HttpClientEngine