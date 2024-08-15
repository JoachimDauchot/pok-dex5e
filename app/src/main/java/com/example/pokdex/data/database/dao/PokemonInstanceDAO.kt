package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.pokdex.data.database.dbObjects.DbPokemonInstance

@Dao
interface PokemonInstanceDAO {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbPokemonInstance)

    @Transaction
    @Delete
    suspend fun delete(item: DbPokemonInstance)
}
