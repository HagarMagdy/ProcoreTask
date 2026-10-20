package com.example.test.data.model

import com.example.test.data.remote.NetworkModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PokemonSerializationTest {
    @Test
    fun decodesCardsAndIgnoresUnrecognizedFields() {
        val response = NetworkModule.json.decodeFromString<PokemonsResponse>(
            """
            {
              "data": [
                {
                  "id": "base1-1",
                  "name": "Alakazam",
                  "hp": "80",
                  "types": ["Psychic"],
                  "images": {
                    "small": "small.png",
                    "large": "large.png"
                  },
                  "set": {"name": "Base"}
                }
              ],
              "page": 1
            }
            """.trimIndent()
        )

        val pokemon = response.pokemons.single()
        assertEquals("base1-1", pokemon.id)
        assertEquals("Alakazam", pokemon.name)
        assertEquals("80", pokemon.hp)
        assertEquals(listOf("Psychic"), pokemon.types)
        assertEquals(PokemonImages("small.png", "large.png"), pokemon.images)
    }

    @Test
    fun decodesCardsWithoutOptionalFields() {
        val response = NetworkModule.json.decodeFromString<PokemonsResponse>(
            """
            {
              "data": [
                {
                  "id": "base1-2",
                  "name": "Chansey",
                  "images": {
                    "small": "small.png",
                    "large": "large.png"
                  }
                }
              ]
            }
            """.trimIndent()
        )

        val pokemon = response.pokemons.single()
        assertNull(pokemon.hp)
        assertNull(pokemon.types)
    }
}
