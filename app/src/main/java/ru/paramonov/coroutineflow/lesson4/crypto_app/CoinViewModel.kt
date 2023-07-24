package ru.paramonov.coroutineflow.lesson4.crypto_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository

    val state: LiveData<State> = repository.getCoinList()
        // фильтруем коллекцию, чтобы она была не пустая
        .filter { it.isNotEmpty() }
        // преобразовываем Flow<List<Coin>>
        .map { State.Content(listCoin = it) as State }
        // во время подписки на flow имитим state loading
        .onStart { emit(State.Loading) }
        // превращаем Flow<State> в LiveData<State>
        .asLiveData()
}