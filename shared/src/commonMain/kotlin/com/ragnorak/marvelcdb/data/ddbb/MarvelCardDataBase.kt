package com.ragnorak.marvelcdb.data.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity

import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [MarvelFavouriteCardEntity::class],
    version = 1
)
@ConstructedBy(MarvelCardDataBaseConstructor::class)
abstract class MarvelCardDataBase: RoomDatabase() {
    abstract fun marvelCardDao(): MarvelCardDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MarvelCardDataBaseConstructor : RoomDatabaseConstructor<MarvelCardDataBase> {
    override fun initialize(): MarvelCardDataBase
}