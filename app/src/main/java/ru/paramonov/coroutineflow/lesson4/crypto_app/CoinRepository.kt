package ru.paramonov.coroutineflow.lesson4.crypto_app

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object CoinRepository {

    private val coinNames = listOf("BTC", "ETH", "USDT", "BNB", "USDC")
    private val coinList = mutableListOf<Coin>()

    private val updateEvents = MutableSharedFlow<Unit>()

    fun getCoinList(): Flow<List<Coin>> = flow {
        delay(3000)
        generateCoinList()
        emit(coinList.toList())
        updateEvents.collect {
            delay(3000)
            generateCoinList()
            emit(coinList.toList())
        }
    }

    suspend fun updateList() {
        updateEvents.emit(Unit)
    }

    private fun generateCoinList() {
        val prices = buildList {
            repeat(coinNames.size) {
                add(Random.nextInt(1000, 2000))
            }
        }
        val newData = buildList {
            for ((index, coinName) in coinNames.withIndex()) {
                val price = prices[index]
                val coin = Coin(id = index, name = coinName, price = price)
                add(coin)
            }
        }
        coinList.clear()
        coinList.addAll(newData)
    }
}