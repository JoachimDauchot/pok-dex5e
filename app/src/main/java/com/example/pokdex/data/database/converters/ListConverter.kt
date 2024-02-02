package com.example.pokdex.data.database.converters

import androidx.room.TypeConverter
import com.example.pokdex.data.database.fromJson
import com.google.gson.Gson

class ListConverter {

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
}
