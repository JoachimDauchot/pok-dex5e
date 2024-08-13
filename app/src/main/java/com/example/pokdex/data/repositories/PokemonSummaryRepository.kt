package com.example.pokdex.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.pokdex.R
import com.example.pokdex.data.ImageStorageManager
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dbObjects.asDbPokemonSummary
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.database.dbObjects.asDomainObjects
import com.example.pokdex.data.dtos.asDomainObjects
import com.example.pokdex.data.network.PokemonService
import com.example.pokdex.data.network.getSummariesAsFlow
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL

interface PokemonSummaryRepository {
    suspend fun insert(item: PokemonSummary)
    fun getSummaries(): Flow<List<PokemonSummary>>
    suspend fun loadSummaryImage(index: String): Bitmap
    suspend fun refresh(): ArrayList<Int>

    suspend fun getSummary(name: String): PokemonSummary
    suspend fun fetchSummaryImage(index: String): Bitmap
}

class PersistPokemonSummaryToDb(
    private val pokemonSummaryDAO: PokemonSummaryDAO,
    private val pokemonService: PokemonService,
    private val context: Context,
) : PokemonSummaryRepository {
    override suspend fun insert(item: PokemonSummary) {
        pokemonSummaryDAO.insert(item.asDbPokemonSummary())
    }
    override fun getSummaries(): Flow<List<PokemonSummary>> {
        val result = pokemonSummaryDAO.getSummaries().map {
            it.asDomainObjects()
        }
        return result
    }

    override suspend fun getSummary(name: String): PokemonSummary {
        val result = pokemonSummaryDAO.getSummary(name)
        return result.asDomainObject()
    }
    override suspend fun refresh(): ArrayList<Int> {
        val indices: ArrayList<Int> = ArrayList<Int>()
        indices.clear()
        try {
            pokemonService.getSummariesAsFlow().collect {
                for (summary in it.asDomainObjects()) {
                    insert(summary)
                    indices.add(summary.index)
                    println(summary)
                }
            }
        } catch (e: Exception) {
            Log.i("API", "API is down")
        }
        return indices
    }

    override suspend fun fetchSummaryImage(index: String): Bitmap {
        try {
            val url = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$index.png")
            val bitmap: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            ImageStorageManager.saveToInternalStorage(context = context, bitmap, "summary_$index.png")

            return bitmap
        } catch (e: Exception) {
            Log.i("image saving", "Failed to retrieve and save image $index at https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$index.png")
            Log.i("error fetchSummary", e.toString())
        }
        return BitmapFactory.decodeResource(context.resources, R.drawable.pok__ball_icon_svg)
    }

    override suspend fun loadSummaryImage(index: String): Bitmap {
        val fileName = "summary_$index.png"
        try {
            val image = ImageStorageManager.getImageFromInternalStorage(context, fileName)
            if (image != null) return image
        } catch (e: Exception) {
            Log.i("imageRetrieval", "Image $fileName does not exist")
            Log.i("error loadSummary", e.toString())
        }
        return fetchSummaryImage(index)
    }
}
