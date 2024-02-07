package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.APIVersion

@Entity(tableName = "apiversion")
data class DbAPIVersion(
    @PrimaryKey()
    var id: Int = 1,
    var version: String = "",
    var wasDownloadedFully: Boolean = false,
)

fun APIVersion.asDbObject(): DbAPIVersion {
    return DbAPIVersion(id = 1, version = version, wasDownloadedFully = false)
}

fun DbAPIVersion.asDomainObject(): APIVersion {
    return APIVersion(version = version, wasDownloadedFully = wasDownloadedFully)
}
