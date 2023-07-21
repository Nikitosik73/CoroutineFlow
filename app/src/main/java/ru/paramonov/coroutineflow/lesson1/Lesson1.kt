package ru.paramonov.coroutineflow.lesson1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

suspend fun main() {
    val numbers = listOf(2, 22, 45, 3, 5, 6, 7, 9, 10, 11, 12, 13, 17, 19, 88, 37).asFlow()

    numbers
        .filter { it.isPrime() }
        .filter { it > 3 }
        .map {
            println("Map")
            "Numbers: $it"
        }
        .collect { println(it) }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}