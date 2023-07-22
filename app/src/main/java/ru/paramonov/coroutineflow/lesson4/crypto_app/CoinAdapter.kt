package ru.paramonov.coroutineflow.lesson4.crypto_app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.paramonov.coroutineflow.R
import ru.paramonov.coroutineflow.databinding.ItemCoinBinding

class CoinAdapter : ListAdapter<Coin, CoinViewHolder>(
    CallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply { return CoinViewHolder(this) }
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinItem = getItem(position)
        val binding = holder.binding
        with(binding) {
            tvCoinName.text = coinItem.name
            tvCoinPrice.text = root.context.getString(
                R.string.price_coin, coinItem.price.toString()
            )
        }
    }
}