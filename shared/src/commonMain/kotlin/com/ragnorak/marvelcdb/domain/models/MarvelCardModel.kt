package com.ragnorak.marvelcdb.domain.models

import kotlinx.serialization.SerialName

data class MarvelCardModel(
    val packCode: String = "",
    val typeCode: String = "",
    val factionCode: String = "",
    val cardSetCode: String = "",
    val linkedCode: String = "",
    val position: Int = 0,
    val code: String = "",
    val name: String = "",
    val text: String = "",
    val health: Int = 0,
    val thwart: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val traits: String = "",
)
