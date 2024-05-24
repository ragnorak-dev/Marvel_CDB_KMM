package com.ragnorak.marvelcdb.data.ddbb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ragnorak.marvelcdb.data.datasource.entities.MarvelFavouriteCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelCardDao {

    @Upsert
    suspend fun upsert(marvelCard: MarvelFavouriteCardEntity)

    @Delete
    suspend fun delete(marvelCard: MarvelFavouriteCardEntity)

    @Query(value = "DELETE FROM MarvelFavouriteCardEntity")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM MarvelFavouriteCardEntity")
    fun getAll(): Flow<List<MarvelFavouriteCardEntity>>
}