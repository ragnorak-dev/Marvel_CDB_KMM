package com.ragnorak.marvelcdb.data.datasource.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarvelCardResponse(
    @SerialName("pack_code")
    val packCode: String = "",
    @SerialName("type_code")
    val typeCode: String = "",
    @SerialName("faction_code")
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
    val imagesrc: String? = null,
)
