package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokdex.data.database.dbObjects.DbAbility

@Dao
interface AbilityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbAbility)

    @Query("SELECT * from abilities WHERE name = :name ")
    suspend fun getAbility(name: String): DbAbility
}
