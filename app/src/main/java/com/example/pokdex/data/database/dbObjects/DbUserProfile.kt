package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserProfile")
data class DbUserProfile(
    @PrimaryKey(autoGenerate = true)
    var userId: Int? = null,
    var name: String?,
    var level: Int?,
)
