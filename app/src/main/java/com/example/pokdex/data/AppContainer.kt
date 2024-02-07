package com.example.pokdex.data

import android.content.Context
import androidx.room.Room
import com.example.pokdex.data.database.PokedexDatabase
import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.network.APIVersionService
import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.data.repositories.APIVersionRepository
import com.example.pokdex.data.repositories.PersistAPIVersionToDb
import com.example.pokdex.data.repositories.PersistPokemonSummaryToDb
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonSummaryRepository: PokemonSummaryRepository
    val apiVersionRepository: APIVersionRepository
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

    // inject services
    private val pokemonSummaryService by lazy {
        retrofit.create(PokemonSummaryService::class.java)
    }

    private val apiVersionService by lazy {
        retrofit.create(APIVersionService::class.java)
    }

    // create db
    private val pokedexDB: PokedexDatabase by lazy {
        Room.databaseBuilder(applicationContext, PokedexDatabase::class.java, name = "pokedex_database")
            .build()
    }

    // inject DAO
    private val pokemonSummaryDAO: PokemonSummaryDAO by lazy {
        pokedexDB.pokemonSummaryDAO()
    }

    private val apiVersionDAO: APIVersionDAO by lazy {
        pokedexDB.aPIVersionDAO()
    }

    // inject repositories
    override val pokemonSummaryRepository: PokemonSummaryRepository by lazy {
        PersistPokemonSummaryToDb(pokemonSummaryDAO = pokemonSummaryDAO, pokemonSummaryService = pokemonSummaryService, context = applicationContext)
    }

    override val apiVersionRepository: APIVersionRepository by lazy {
        PersistAPIVersionToDb(apiVersionDAO = apiVersionDAO, apiVersionService = apiVersionService)
    }
}
