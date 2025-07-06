package com.hynekbraun.rickandmorty.shared.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoriteCharactersDao {

    @Query("SELECT * FROM favorite_characters")
    fun getAll(): Flow<List<FavoriteCharacterEntity>>

    @Query("SELECT * FROM favorite_characters WHERE id = :id")
    suspend fun findById(id: String): FavoriteCharacterEntity?

    @Insert(entity = FavoriteCharacterEntity::class)
    suspend fun insert(characterId: FavoriteCharacterEntity)

    @Delete
    suspend fun delete(characterId: FavoriteCharacterEntity)
}