package ru.paramonov.coroutineflow.lesson4.crypto_app

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object CoinRepository {

    private val coinNames = listOf("BTC", "ETH", "USDT", "BNB", "USDC")
    private val coinList = mutableListOf<Coin>()

    fun getCoinList(): Flow<List<Coin>> = flow {
        while (true) {
            delay(3000)
            generateCoinList()
            emit(coinList.toList())
            delay(3000)
        }
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