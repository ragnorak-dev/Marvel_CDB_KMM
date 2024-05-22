package com.ragnorak.marvelcdb.data.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import kotlinx.coroutines.flow.MutableStateFlow

@Entity
data class MarvelFavouriteCardEntity(
    @PrimaryKey
    val code: String = "",
    val position: Int = 0,
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
) {
    fun toModel(): MarvelCardModel =
        MarvelCardModel(
            code = code,
            position = position,
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
            imagesrc = imagesrc,
            isFavourite = MutableStateFlow(true)
        )
}
