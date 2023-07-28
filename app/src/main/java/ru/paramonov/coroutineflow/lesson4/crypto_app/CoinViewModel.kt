package ru.paramonov.coroutineflow.lesson4.crypto_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository

    val state: Flow<State> = repository.getCoinList()
        .filter { it.isNotEmpty() }
        .map { State.Content(listCoin = it) as State }
        .onStart { emit(State.Loading) }

    fun updateList() {
        viewModelScope.launch {
            repository.updateList()
        }
    }
}