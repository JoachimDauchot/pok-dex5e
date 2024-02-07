package com.example.pokdex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokdex.data.database.converters.ListConverter
import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dbObjects.DbAPIVersion
import com.example.pokdex.data.database.dbObjects.DbPokemonSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(entities = [DbPokemonSummary::class, DbAPIVersion::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonSummaryDAO(): PokemonSummaryDAO
    abstract fun aPIVersionDAO(): APIVersionDAO
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
