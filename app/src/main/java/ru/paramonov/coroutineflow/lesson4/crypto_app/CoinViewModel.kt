package ru.paramonov.coroutineflow.lesson4.crypto_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository

    val state: Flow<State> = repository.getCoinList()
        .filter { it.isNotEmpty() }
        .map { State.Content(listCoin = it) as State }
        .onStart {
            Log.d("CoinViewModel", "Started")
            emit(State.Loading)
        }
        .onEach { Log.d("CoinViewModel", "onEach") }
        .onCompletion { Log.d("CoinViewModel", "Complete") }
}