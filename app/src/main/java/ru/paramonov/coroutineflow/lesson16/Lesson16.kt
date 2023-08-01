package ru.paramonov.coroutineflow.lesson16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry

suspend fun main() {
    loadDataFlow()
        .map { State.Content(value = it) as State }
        .onStart { emit(State.Loading) }
        .retry(2) { true }
        .catch { emit(State.Error) }
        .collect { state ->
            when(state) {
                is State.Content -> {
                    println("Collected: ${state.value}")
                }
                is State.Error -> {
                    println("Something went wrong")
                }
                is State.Loading -> {
                    println("Loading...")
                }
            }
        }
}

fun loadDataFlow(): Flow<Int> = flow {
    repeat(5) {
        delay(500)
        println("Collected: $it")
    }
    throw RuntimeException("Exception from flow block")
}

sealed class State {

    data class Content(val value: Int) : State()
    object Loading : State()
    object Error : State()
}