package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.MoveDTO
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface MoveService {
    @GET("api/Moves")
    suspend fun getMoves(): List<MoveDTO>
}

fun MoveService.getMovesAsFlow() = flow { emit(getMoves()) }
