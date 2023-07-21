package ru.paramonov.coroutineflow.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

suspend fun main() {
    getFlowByBuilderFlow()
        .filter { it.isPrime() }
        .filter { it > 3 }
        .map {
            println("Map")
            "Numbers: $it"
        }
        .collect { println(it) }
}

fun getFlowByOfFlowBuilder(): Flow<Int> {
    return flowOf(2, 22, 45, 3, 5, 6, 7, 9, 10, 11, 12, 13, 17, 19, 88, 37)
}

fun getFlowByBuilderFlow(): Flow<Int> {
    val numbers = listOf(2, 22, 45, 3, 5, 6, 7, 9, 10, 11, 12, 13, 17, 19, 88, 37)
    return flow {
        numbers.forEach {
            emit(it)
        }
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}