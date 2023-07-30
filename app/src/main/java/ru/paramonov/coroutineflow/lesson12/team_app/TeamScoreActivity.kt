package ru.paramonov.coroutineflow.lesson12.team_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.paramonov.coroutineflow.R
import ru.paramonov.coroutineflow.databinding.ActivityTeamScoreBinding

class TeamScoreActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTeamScoreBinding.inflate(layoutInflater)
    }

    private val viewModel: TeamScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        setupListeners()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { viewState ->
                    when (viewState) {
                        is TeamScoreState.Game -> {
                            binding.team1Score.text = viewState.score1.toString()
                            binding.team2Score.text = viewState.score2.toString()
                        }

                        is TeamScoreState.Winner -> {
                            binding.team1Score.text = viewState.score1.toString()
                            binding.team2Score.text = viewState.score2.toString()
                            Toast.makeText(
                                this@TeamScoreActivity,
                                "Winner: ${viewState.winnerTeam}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.team1Logo.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_1)
        }
        binding.team2Logo.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_2)
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, TeamScoreActivity::class.java)
    }
}