package com.ragnorak.marvelcdb.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDataBase

fun getFavouriteMarvelCards(context: Context): MarvelCardDataBase {
    val dbFile = context.getDatabasePath("marvelCardsFavourites.db")
    return Room.databaseBuilder<MarvelCardDataBase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}