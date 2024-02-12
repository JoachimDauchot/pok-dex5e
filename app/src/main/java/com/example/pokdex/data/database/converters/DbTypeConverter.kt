package com.example.pokdex.data.database.converters

import android.util.Log
import androidx.room.TypeConverter
import com.example.pokdex.data.database.dbObjects.DbAttributes
import com.example.pokdex.data.database.dbObjects.DbDamage
import com.example.pokdex.data.database.dbObjects.DbEvolve
import com.example.pokdex.data.database.dbObjects.DbMoves
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

    @TypeConverter
    fun toStringDbAttribute(value: DbAttributes): String {
        Log.i("attr", value.toString())
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringDbAttribute(value: String): DbAttributes {
        return try {
            Gson().fromJson<DbAttributes>(value)
        } catch (e: Exception) {
            DbAttributes(strength = 0, dexterity = 0, constitution = 0, intelligence = 0, wisdom = 0, charisma = 0)
        }
    }

    @TypeConverter
    fun toStringDbMoves(value: DbMoves): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringDbMoves(value: String): DbMoves {
        return try {
            Gson().fromJson<DbMoves>(value)
        } catch (e: Exception) {
            DbMoves(emptyMap(), emptyList(), emptyList(), emptyList())
        }
    }

    @TypeConverter
    fun toStringDbEvolve(value: DbEvolve): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringDbEvolve(value: String): DbEvolve {
        return try {
            Gson().fromJson<DbEvolve>(value)
        } catch (e: Exception) {
            DbEvolve(emptyList(), emptyList(), 0, 0, 0, 0, "")
        }
    }
}
