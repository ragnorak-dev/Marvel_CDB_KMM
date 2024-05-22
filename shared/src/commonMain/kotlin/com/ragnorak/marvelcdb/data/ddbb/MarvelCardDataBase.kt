package com.ragnorak.marvelcdb.data.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity

@Database(
    entities = [MarvelFavouriteCardEntity::class],
    version = 1
)
abstract class MarvelCardDataBase: RoomDatabase() {
    abstract fun marvelCardDao(): MarvelCardDao
}