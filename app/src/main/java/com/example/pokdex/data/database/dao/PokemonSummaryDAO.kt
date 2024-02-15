package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.dbObjects.DbPokemonSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonSummaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbPokemonSummary)

    @Query("SELECT * from pokemonsummaries ORDER BY `index` ASC")
    fun getSummaries(): Flow<List<DbPokemonSummary>>

    @Query("SELECT * from pokemonsummaries WHERE name = :name LIMIT 1")
    suspend fun getSummary(name: String): DbPokemonSummary
}
