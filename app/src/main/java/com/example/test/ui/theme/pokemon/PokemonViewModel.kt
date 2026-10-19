package com.example.test.ui.theme.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.remote.NetworkModule
import com.example.test.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    val repo: PokemonRepository = PokemonRepository(NetworkModule.api)
) : ViewModel() {

    private var _state = MutableStateFlow<PokemonUIState>(PokemonUIState.Loading)
    val state: StateFlow<PokemonUIState> = _state.asStateFlow()

    init {
        load()
    }

    fun load() {
        _state.value = PokemonUIState.Loading
        viewModelScope.launch {
            repo.getPokemons().fold(
                onSuccess = { _state.value = PokemonUIState.Success(it) },
                onFailure = {
                    _state.value = PokemonUIState.Failure(it.message ?: " Something wrong happened")
                }
            )
        }
    }


}