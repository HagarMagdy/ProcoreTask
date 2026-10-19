package com.example.test.data.remote

import com.example.test.data.model.Pokemon
import retrofit2.http.GET


interface PokemonApi {

    @GET("cards")
    suspend fun getPokemons(): List<Pokemon>

}