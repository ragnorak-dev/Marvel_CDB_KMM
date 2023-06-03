package com.ragnorak.marvelcdb.domain.models.mappers

import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

internal fun MarvelCardResponse.toModel(): MarvelCardModel =
    MarvelCardModel(
        packCode = packCode,
        typeCode = typeCode,
        factionCode = factionCode,
        cardSetCode = cardSetCode,
        linkedCode = linkedCode,
        position = position,
        code = code,
        name = name,
        text = text,
        health = health,
        thwart = thwart,
        attack = attack,
        defense = defense,
        traits = traits
    )