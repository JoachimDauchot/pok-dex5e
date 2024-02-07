package com.example.pokdex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokdex.data.database.converters.DbTypeConverter
import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dao.MoveDAO
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dbObjects.DbAPIVersion
import com.example.pokdex.data.database.dbObjects.DbMove
import com.example.pokdex.data.database.dbObjects.DbPokemonSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(entities = [DbPokemonSummary::class, DbAPIVersion::class, DbMove::class], version = 1)
@TypeConverters(DbTypeConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonSummaryDAO(): PokemonSummaryDAO
    abstract fun aPIVersionDAO(): APIVersionDAO
    abstract fun moveDAO(): MoveDAO
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
