package ru.paramonov.coroutineflow.lesson3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

suspend fun main() {
    getFlowByBuilderFlow()
        .filter { it.isPrime() }
        .map { "Numbers: $it" }
        .collect { println(it) }
}

fun getFlowByFlowOfBuilder(): Flow<Int> = flowOf(
    1,23, 3, 7, 9, 5, 11
)

fun getFlowByBuilderFlow(): Flow<Int> {
    val firstFlow = getFlowByFlowOfBuilder()
    return flow {
//        firstFlow.collect {
//            println("Emitted from first flow: $it")
//            emit(it)
//        }
        emitAll(firstFlow)
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(100)
        if (this % i == 0) return false
    }
    return true
}
