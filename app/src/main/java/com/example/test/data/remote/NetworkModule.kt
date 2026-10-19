package com.example.test.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkModule {
    val json = Json { ignoreUnknownKeys = true }

    val api: PokemonApi = Retrofit.Builder().baseUrl("https://api.pokemontcg.io/v2/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(PokemonApi::class.java)
}