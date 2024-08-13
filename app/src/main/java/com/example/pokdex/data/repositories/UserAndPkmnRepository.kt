package com.example.pokdex.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.pokdex.R
import com.example.pokdex.data.ImageStorageManager
import com.example.pokdex.data.database.dao.PokemonInstanceDAO
import com.example.pokdex.data.database.dao.UserDAO
import com.example.pokdex.data.database.relations.asDomainObject
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.model.UserProfile
import com.example.pokdex.model.asDbObject
import java.net.URL

interface UserAndPkmnRepository {
    suspend fun insertUser(user: UserProfile)
    suspend fun getUser(): UserProfile
    suspend fun insertPokemonInstance(pokemon: PokemonInstance)
    suspend fun fetchSummaryImage(index: String): Bitmap
    suspend fun loadSummaryImage(index: String): Bitmap
}

class PersistUserOrPokemonToDB(
    private val userDAO: UserDAO,
    private val pokemonInstanceDAO: PokemonInstanceDAO,
    private val context: Context,
) : UserAndPkmnRepository {
    override suspend fun insertUser(user: UserProfile) {
        userDAO.insert(user.asDbObject())
    }

    override suspend fun getUser(): UserProfile {
        return userDAO.getUserProfile().asDomainObject()
    }

    override suspend fun insertPokemonInstance(pokemon: PokemonInstance) {
        pokemonInstanceDAO.insert(pokemon.asDbObject())
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
