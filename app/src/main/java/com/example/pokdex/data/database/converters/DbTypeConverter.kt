package com.example.pokdex.data.database.converters

import androidx.room.TypeConverter
import com.example.pokdex.data.database.dbObjects.DbDamage
import com.example.pokdex.data.database.fromJson
import com.google.gson.Gson

class DbTypeConverter {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value)
        } catch (e: Exception) {
            emptyList<String>()
        }
    }

    @TypeConverter
    fun fromStringDamageMap(value: Map<Int, DbDamage>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringDamageMap(value: String): Map<Int, DbDamage> {
        return try {
            Gson().fromJson<Map<Int, DbDamage>>(value)
        } catch (e: Exception) {
            emptyMap<Int, DbDamage>()
        }
    }
}
