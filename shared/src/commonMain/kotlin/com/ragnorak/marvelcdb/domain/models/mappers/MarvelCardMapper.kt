package com.ragnorak.marvelcdb.domain.models.mappers

import com.ragnorak.marvelcdb.HOST_URL
import com.ragnorak.marvelcdb.data.datasource.responses.MarvelCardResponse
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel

internal fun MarvelCardResponse.toModel(): MarvelCardModel =
    MarvelCardModel(
        packCode = packCode,
        typeCode = typeCode,
        factionCode = factionCode,
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
        imagesrc = if (imagesrc.isBlank()) {
            null
        } else {
            HOST_URL.plus(imagesrc)
        }
    )