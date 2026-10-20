package com.example.test.data.remote

import com.example.test.data.model.PokemonsResponse
import retrofit2.http.GET


interface PokemonApi {

    @GET("cards")
    suspend fun getPokemons(): PokemonsResponse

}