package ru.paramonov.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.paramonov.coroutineflow.databinding.ActivityMainBinding
import ru.paramonov.coroutineflow.lesson2.UsersActivity
import ru.paramonov.coroutineflow.lesson4.crypto_app.CoinActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
        binding.buttonCryptoActivity.setOnClickListener {
            startActivity(CoinActivity.newIntent(this))
        }
    }
}