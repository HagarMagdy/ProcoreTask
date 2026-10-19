package com.example.test.ui.theme.pokemon

import com.example.test.data.model.Pokemon

sealed interface PokemonUIState {
    object Loading : PokemonUIState
    data class Failure(val message: String) : PokemonUIState
    data class Success(val pokemons: List<Pokemon>) : PokemonUIState
}