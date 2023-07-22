package ru.paramonov.coroutineflow.lesson4.crypto_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import ru.paramonov.coroutineflow.R
import ru.paramonov.coroutineflow.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<CoinViewModel>()

    private val coinAdapter = CoinAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        observerViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCoin.adapter = coinAdapter
        binding.recyclerViewCoin.itemAnimator = null
    }

    private fun observerViewModel() {
        viewModel.state.observe(this) { viewState ->
            when(viewState) {
                is State.Initial -> {
                    binding.progressCircular.isVisible = false
                }
                is State.Loading -> {
                    binding.progressCircular.isVisible = true
                }
                is State.Content -> {
                    binding.progressCircular.isVisible = false
                    coinAdapter.submitList(viewState.listCoin)
                }
            }
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, CoinActivity::class.java)
    }
}