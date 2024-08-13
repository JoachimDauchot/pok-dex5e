package com.example.pokdex.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.pokdex.data.database.PokedexDatabase
import com.example.pokdex.data.database.dao.APIVersionDAO
import com.example.pokdex.data.database.dao.AbilityDAO
import com.example.pokdex.data.database.dao.MoveDAO
import com.example.pokdex.data.database.dao.PokemonDetailDAO
import com.example.pokdex.data.database.dao.PokemonInstanceDAO
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dao.UserDAO
import com.example.pokdex.data.network.APIVersionService
import com.example.pokdex.data.network.AbilityService
import com.example.pokdex.data.network.MoveService
import com.example.pokdex.data.network.PokemonService
import com.example.pokdex.data.repositories.APIVersionRepository
import com.example.pokdex.data.repositories.AbilityRepository
import com.example.pokdex.data.repositories.MoveRepository
import com.example.pokdex.data.repositories.PersistAPIVersionToDb
import com.example.pokdex.data.repositories.PersistAbilityToDB
import com.example.pokdex.data.repositories.PersistMoveToDB
import com.example.pokdex.data.repositories.PersistPokemonDetailToDB
import com.example.pokdex.data.repositories.PersistPokemonSummaryToDb
import com.example.pokdex.data.repositories.PersistUserOrPokemonToDB
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.data.repositories.UserAndPkmnRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonSummaryRepository: PokemonSummaryRepository
    val apiVersionRepository: APIVersionRepository
    val moveRepository: MoveRepository
    val pokemonDetailRepository: PokemonDetailRepository
    val abilityRepository: AbilityRepository
    val userAndPkmnRepository: UserAndPkmnRepository
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
    private val pokemonService by lazy {
        Log.i("Interweb", "creating pokemonservice")
        retrofit.create(PokemonService::class.java)
    }

    private val apiVersionService by lazy {
        Log.i("Interweb", "creating apiVersionService")
        retrofit.create(APIVersionService::class.java)
    }

    private val moveService by lazy {
        Log.i("Interweb", "creating moveService")
        retrofit.create(MoveService::class.java)
    }

    private val abilityService by lazy {
        Log.i("Interweb", "creating abilityService")
        retrofit.create(AbilityService::class.java)
    }

    // create db
    private val pokedexDB: PokedexDatabase by lazy {

        Room.databaseBuilder(applicationContext, PokedexDatabase::class.java, name = "pokedex_database").build()
    }

    // inject DAO
    private val pokemonSummaryDAO: PokemonSummaryDAO by lazy {
        pokedexDB.pokemonSummaryDAO()
    }

    private val apiVersionDAO: APIVersionDAO by lazy {
        pokedexDB.aPIVersionDAO()
    }

    private val moveDAO: MoveDAO by lazy {
        pokedexDB.moveDAO()
    }

    private val pokemonDetailDAO: PokemonDetailDAO by lazy {
        pokedexDB.pokemonDetailDAO()
    }

    private val abilityDAO: AbilityDAO by lazy {
        pokedexDB.abilityDAO()
    }

    private val userDAO: UserDAO by lazy {
        pokedexDB.UserDAO()
    }

    private val pokemonInstanceDAO: PokemonInstanceDAO by lazy {
        pokedexDB.pokemonInstanceDAO()
    }

    // inject repositories
    override val pokemonSummaryRepository: PokemonSummaryRepository by lazy {
        PersistPokemonSummaryToDb(pokemonSummaryDAO = pokemonSummaryDAO, pokemonService = pokemonService, context = applicationContext)
    }

    override val apiVersionRepository: APIVersionRepository by lazy {
        PersistAPIVersionToDb(apiVersionDAO = apiVersionDAO, apiVersionService = apiVersionService)
    }

    override val moveRepository: MoveRepository by lazy {
        PersistMoveToDB(moveDAO = moveDAO, moveService = moveService)
    }

    override val pokemonDetailRepository: PokemonDetailRepository by lazy {
        PersistPokemonDetailToDB(pokemonDetailDAO = pokemonDetailDAO, pokemonService = pokemonService)
    }

    override val abilityRepository: AbilityRepository by lazy {
        PersistAbilityToDB(abilityDAO = abilityDAO, abilityService = abilityService)
    }

    override val userAndPkmnRepository: UserAndPkmnRepository by lazy {
        PersistUserOrPokemonToDB(userDAO = userDAO, pokemonInstanceDAO = pokemonInstanceDAO, context = applicationContext)
    }
}
