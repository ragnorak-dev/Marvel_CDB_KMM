package com.ragnorak.marvelcdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform