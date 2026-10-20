package com.example.test.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonsResponse(@SerialName("data") val pokemons: List<Pokemon>)
@Serializable
data class Pokemon (
    val id: String,
    val name: String,
    val hp: String? = null,
    val types: List<String>? = null,
    val images: PokemonImages
)
@Serializable
data class PokemonImages(
    val small: String,
    val large: String
)