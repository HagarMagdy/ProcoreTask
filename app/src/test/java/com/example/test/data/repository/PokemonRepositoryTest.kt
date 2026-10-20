package com.example.test.data.repository

import com.example.test.data.model.Pokemon
import com.example.test.data.model.PokemonImages
import com.example.test.data.model.PokemonsResponse
import com.example.test.data.remote.PokemonApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.fail
import org.junit.Test

class PokemonRepositoryTest {
    @Test
    fun getPokemons_returnsCardsFromApi() = runTest {
        val pokemon = Pokemon(
            id = "base1-1",
            name = "Alakazam",
            images = PokemonImages("small.png", "large.png")
        )
        val repository = PokemonRepository(FakePokemonApi { PokemonsResponse(listOf(pokemon)) })

        val result = repository.getPokemons()

        assertEquals(Result.success(listOf(pokemon)), result)
    }

    @Test
    fun getPokemons_returnsEmptyListWhenApiHasNoCards() = runTest {
        val repository = PokemonRepository(FakePokemonApi { PokemonsResponse(emptyList()) })

        val result = repository.getPokemons()

        assertEquals(Result.success(emptyList<Pokemon>()), result)
    }

    @Test
    fun getPokemons_returnsApiFailure() = runTest {
        val error = IllegalStateException("Request failed")
        val repository = PokemonRepository(FakePokemonApi { throw error })

        val result = repository.getPokemons()

        assertSame(error, result.exceptionOrNull())
    }

    @Test
    fun getPokemons_rethrowsCancellation() = runTest {
        val cancellation = CancellationException("Cancelled")
        val repository = PokemonRepository(FakePokemonApi { throw cancellation })

        try {
            repository.getPokemons()
            fail("Expected cancellation to be rethrown")
        } catch (actual: CancellationException) {
            assertSame(cancellation, actual)
        }
    }

}

private class FakePokemonApi(
    private val response: suspend () -> PokemonsResponse
) : PokemonApi {
    override suspend fun getPokemons(): PokemonsResponse = response()
}
