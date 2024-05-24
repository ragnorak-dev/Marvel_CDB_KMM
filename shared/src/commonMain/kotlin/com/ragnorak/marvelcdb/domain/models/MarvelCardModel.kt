package com.ragnorak.marvelcdb.domain.models

import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import kotlinx.coroutines.flow.MutableStateFlow

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
    val imagesrc: String? = null,
    var isFavourite: Boolean = false,
) {
    fun toEntity(): MarvelFavouriteCardEntity =
        MarvelFavouriteCardEntity(
            position = position,
            code = code,
            name = name,
            subname = subname,
            text = text,
            health = health,
            thwart = thwart,
            attack = attack,
            defense = defense,
            cost = cost,
            quantity = quantity,
            traits = traits,
            imagesrc = imagesrc ?: "",
        )
}
