package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.dbObjects.DbPokemonDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbPokemonDetail)

    @Query("SELECT * from pokemons WHERE `index` = :index ")
    fun getPokemon(index: Int): Flow<DbPokemonDetail>
}
