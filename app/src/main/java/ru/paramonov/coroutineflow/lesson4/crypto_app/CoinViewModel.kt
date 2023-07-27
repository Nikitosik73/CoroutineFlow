package ru.paramonov.coroutineflow.lesson4.crypto_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository

    val state: LiveData<State> = repository.getCoinList()
        .filter { it.isNotEmpty() }
        .map { State.Content(listCoin = it) as State}
        .onStart {
            Log.d("CoinViewModel", "Started")
            emit(State.Loading)
        }
        .onEach { Log.d("CoinViewModel", "onEach") }
        .onCompletion { Log.d("CoinViewModel", "Complete") }
        .asLiveData()

    // тест, как работает asLiveData под капотом
//    private val _state = MutableLiveData<State>(State.Initial)
//    val state: LiveData<State> = _state
//
//    private var job: Job? = null
//    private var isResumed = false
//
//    fun loadData() {
//        isResumed = true
//        if (job != null) return
//        job = repository.getCoinList()
//            .onStart {
//                Log.d("CoinViewModel", "Started")
//                _state.value = State.Loading
//            }
//            .filter { it.isNotEmpty() }
//            .onEach { value ->
//                Log.d("CoinViewModel", "onEach: $value")
//                _state.value = State.Content(listCoin = value)
//            }
//            .onCompletion {
//                Log.d("CoinViewModel", "Completed")
//            }.launchIn(viewModelScope)
//    }
//
//    fun stopData() {
//        viewModelScope.launch {
//            delay(5000)
//            if (!isResumed) {
//                job?.cancel()
//                job = null
//            } else {
//                isResumed = false
//            }
//        }
//    }
}