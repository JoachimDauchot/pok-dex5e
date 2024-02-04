package com.example.pokdex.data.network

import retrofit2.http.GET

interface APIVersionService {
    @GET("/api/version")
    suspend fun getAPIVersion(): String
}