package com.example.test.ui.theme.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonScreen(vm: PokemonViewModel = viewModel()) {

    val state by vm.state.collectAsStateWithLifecycle()

    when (val s= state){
        is PokemonUIState.Loading -> {
            Box(Modifier.fillMaxSize(), Alignment.Center){
                CircularProgressIndicator()
            }
        }

        is PokemonUIState.Failure -> {
            Column(Modifier.fillMaxSize(), Arrangement.Center
                ) {
                Button(onClick = vm::load) { Text("Retry") }
            }
        }

        is PokemonUIState.Success -> LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(s.pokemons, key = { it.id }) { pokemon ->
                Text(
                    text = pokemon.name,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

    }


}