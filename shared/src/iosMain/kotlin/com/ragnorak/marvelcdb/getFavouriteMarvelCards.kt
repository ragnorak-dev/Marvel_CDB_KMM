package com.ragnorak.marvelcdb

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ragnorak.marvelcdb.data.ddbb.MarvelCardDataBase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

fun getFavouriteMarvelCards(): MarvelCardDataBase {
    val documentDirectory = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true).first() as String
    val dbFile = "$documentDirectory/marvelCardsFavourites.db"
    return Room.databaseBuilder<MarvelCardDataBase>(
        name = dbFile
    )
        .setDriver(BundledSQLiteDriver())
        .build()

}