package ru.paramonov.coroutineflow.lesson4.crypto_app

import androidx.recyclerview.widget.DiffUtil

class CallBack : DiffUtil.ItemCallback<Coin>() {

    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }
}