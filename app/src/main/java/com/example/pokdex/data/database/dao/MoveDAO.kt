package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.dbObjects.DbMove
import kotlinx.coroutines.flow.Flow

@Dao
interface MoveDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbMove)

    @Query("SELECT * from moves WHERE name = :name ")
    fun getMove(name: String): Flow<DbMove>
}
