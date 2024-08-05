package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.AbilityDTO
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface AbilityService {
    @GET("api/Ability")
    suspend fun getAbilities(): List<AbilityDTO>
}

fun AbilityService.getAbilitiesAsFlow() = flow { emit(getAbilities()) }
