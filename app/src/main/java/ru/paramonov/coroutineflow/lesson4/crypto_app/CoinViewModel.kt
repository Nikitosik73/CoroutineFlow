package ru.paramonov.coroutineflow.lesson4.crypto_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository

    private val _state = MutableLiveData<State>(State.Initial)
    val state: LiveData<State> = _state

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getCoinList()
                .onStart {
                    val currentState = _state.value
                    if (currentState !is State.Content || currentState.listCoin.isEmpty()) {
                        _state.value = State.Loading
                    }
                }
                .filter { it.isNotEmpty() }
                .onEach { list ->
                    _state.value = State.Content(listCoin = list)
                }.collect()
        }
    }
}