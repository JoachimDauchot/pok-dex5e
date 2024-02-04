package com.example.pokdex.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.pokdex.R
import com.example.pokdex.data.ImageStorageManager
import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dbObjects.asDbPokemonSummary
import com.example.pokdex.data.database.dbObjects.asDomainObjects
import com.example.pokdex.data.dtos.asDomainObjects
import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.data.network.getSummariesAsFlow
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL

interface PokemonSummaryRepository {
    suspend fun insert(item: PokemonSummary)
    fun getSummaries(): Flow<List<PokemonSummary>>
    fun retrieveImage(context: Context, fileName: String): Bitmap
    suspend fun refresh(context: Context)
}

class PersistPokemonSummaryToDb(
    private val pokemonSummaryDAO: PokemonSummaryDAO,
    private val pokemonSummaryService: PokemonSummaryService,
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
    override suspend fun refresh(context: Context) {
        pokemonSummaryService.getSummariesAsFlow().collect {
            for (summary in it.asDomainObjects()) {
                insert(summary)
                println(summary)
                try {
                    var url = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/${summary.index}.png")
                    var bitmap: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    ImageStorageManager.saveToInternalStorage(context = context, bitmap, "${summary.index}.png")
                } catch (e: Exception) {
                    Log.i("image saving", "Failed to retrieve and save image ${summary.index}")
                }
            }
        }
    }
    override fun retrieveImage(context: Context, fileName: String): Bitmap {
        try {
            val image: Bitmap? = ImageStorageManager.getImageFromInternalStorage(context, fileName)
            if (image != null) {
                return image
            }
        } catch (e: Exception) {
            Log.i("imageRetrieval", "Image $fileName does not exist")
        }
        return BitmapFactory.decodeResource(context.resources, R.drawable.pok__ball_icon_svg)
    }
}
