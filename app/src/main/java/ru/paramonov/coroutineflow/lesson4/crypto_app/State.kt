package ru.paramonov.coroutineflow.lesson4.crypto_app

sealed class State {
    object Initial : State()
    object Loading : State()
    class Content(val listCoin: List<Coin> = emptyList()) : State()
}