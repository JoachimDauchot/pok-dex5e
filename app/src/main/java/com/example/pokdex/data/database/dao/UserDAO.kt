package com.example.pokdex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokdex.data.database.dbObjects.DbUserProfile
import com.example.pokdex.data.database.relations.DbUserWithPkmn

@Dao
interface UserDAO {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbUserProfile)

    @Transaction
    @Query("SELECT * from UserProfile LIMIT 1")
    suspend fun getUserProfile(): DbUserWithPkmn
}
