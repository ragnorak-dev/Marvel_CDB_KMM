package com.ragnorak.marvelcdb

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDataBase
import platform.Foundation.NSHomeDirectory

fun getFavouriteMarvelCards(): MarvelCardDataBase {
    val dbFile = NSHomeDirectory() + "/marvelCardsFavourites.db"
    return Room.databaseBuilder<MarvelCardDataBase>(
        name = dbFile,
        factory = { MarvelCardDataBase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()

}