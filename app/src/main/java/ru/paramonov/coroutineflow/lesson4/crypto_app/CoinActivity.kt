package ru.paramonov.coroutineflow.lesson4.crypto_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
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

        binding.btnUpdateData.setOnClickListener {
            viewModel.updateList()
        }
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { viewState ->
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
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, CoinActivity::class.java)
    }
}