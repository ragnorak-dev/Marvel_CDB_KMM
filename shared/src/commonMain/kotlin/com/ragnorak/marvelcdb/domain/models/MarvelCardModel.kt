package com.ragnorak.marvelcdb.domain.models

data class MarvelCardModel(
    val packCode: String = "",
    val typeCode: String = "",
    val factionCode: String = "",
    val position: Int = 0,
    val code: String = "",
    val name: String = "",
    val subname: String = "",
    val text: String = "",
    val health: Int = 0,
    val thwart: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val cost: Int = 0,
    val quantity: Int = 0,
    val traits: String = "",
    val imagesrc: String = "",
)
