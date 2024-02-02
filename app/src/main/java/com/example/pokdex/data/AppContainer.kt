package com.example.pokdex.data

import android.content.Context
import androidx.room.Room
import com.example.pokdex.data.database.PokedexDatabase
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.data.repositories.PersistPokemonSummaryToDb
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonSummaryRepository: PokemonSummaryRepository
}

class DefaultAppContainer(
    private val applicationContext: Context,
) : AppContainer {
    private val baseUrl = "http://10.0.2.2:5262"
    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(baseUrl)
        .build()

    private val pokemonSummaryService by lazy {
        retrofit.create(PokemonSummaryService::class.java)
    }

    private val pokedexDB: PokedexDatabase by lazy {
        Room.databaseBuilder(applicationContext, PokedexDatabase::class.java, name = "pokedex_database")
            .build()
    }

    private val pokemonSummaryDAO: PokemonSummaryDAO by lazy {
        pokedexDB.pokemonSummaryDAO()
    }
    override val pokemonSummaryRepository: PokemonSummaryRepository by lazy {
        PersistPokemonSummaryToDb(pokemonSummaryDAO = pokemonSummaryDAO, pokemonSummaryService = pokemonSummaryService)
    }
}
