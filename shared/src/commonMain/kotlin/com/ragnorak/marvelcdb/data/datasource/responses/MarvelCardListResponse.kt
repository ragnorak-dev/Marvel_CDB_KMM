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
    @SerialName("card_set_code")
    val cardSetCode: String = "",
    @SerialName("linked_to_code")
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
