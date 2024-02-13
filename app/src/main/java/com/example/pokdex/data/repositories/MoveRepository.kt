package com.example.pokdex.data.repositories

import android.util.Log
import com.example.pokdex.data.database.dao.MoveDAO
import com.example.pokdex.data.dtos.asDomainObject
import com.example.pokdex.data.network.MoveService
import com.example.pokdex.data.network.getMovesAsFlow
import com.example.pokdex.model.Move
import com.example.pokdex.model.asDbObject
import kotlinx.coroutines.flow.collect
import java.net.SocketTimeoutException

interface MoveRepository {
    suspend fun insert(item: Move)

    suspend fun retrieveMoves()
}

public class PersistMoveToDB(
    private val moveDAO: MoveDAO,
    private val moveService: MoveService,
) : MoveRepository {
    override suspend fun insert(item: Move) {
        moveDAO.insert(item.asDbObject())
    }

    override suspend fun retrieveMoves() {
        try {
            moveService.getMovesAsFlow().collect() {
                for (move in it) {
                    insert(move.asDomainObject())
                }
            }
        } catch (e: Exception) {
            Log.i("API", "API is down")
        }
    }
}
