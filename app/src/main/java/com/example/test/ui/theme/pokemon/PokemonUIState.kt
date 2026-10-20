package com.example.test.ui.theme.pokemon

import com.example.test.data.model.Pokemon

sealed interface PokemonUIState {
    object Loading : PokemonUIState //  // no data to hold, so yes singleton is enough
    data class Failure(val message: String) : PokemonUIState
    data class Success(val pokemons: List<Pokemon>) : PokemonUIState
}