package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.dbObjects.DbAPIVersion
@Dao
interface APIVersionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbAPIVersion)

    @Query("SELECT * from apiversion LIMIT 1")
    fun getAPIVersion(): DbAPIVersion
}
