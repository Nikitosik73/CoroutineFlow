package ru.paramonov.coroutineflow.lesson2

import kotlinx.coroutines.delay

object UsersRepository {

    private val users = mutableListOf("Nikita", "Alex", "Max")

    suspend fun addUser(user: String) {
        delay(100)
        users.add(user)
    }

    suspend fun loadUser(): List<String> {
        delay(100)
        return users.toList()
    }
}