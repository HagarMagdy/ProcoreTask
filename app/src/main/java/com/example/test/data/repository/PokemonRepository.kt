package com.example.test.data.repository

import com.example.test.data.model.Pokemon
import com.example.test.data.remote.PokemonApi
import kotlinx.coroutines.CancellationException

class PokemonRepository(val api: PokemonApi) {
    suspend fun getPokemons(): Result<List<Pokemon>> = try {
        Result.success(api.getPokemons().pokemons)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}