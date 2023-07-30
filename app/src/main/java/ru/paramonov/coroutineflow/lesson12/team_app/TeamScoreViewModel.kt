package ru.paramonov.coroutineflow.lesson12.team_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TeamScoreViewModel : ViewModel() {

    private val _state = MutableLiveData<TeamScoreState>(TeamScoreState.Game(0, 0))
    val state: LiveData<TeamScoreState> = _state

    fun increaseScore(team: Team) = viewModelScope.launch {
        val currentState = _state.value
        if (currentState is TeamScoreState.Game) {
            if (team == Team.TEAM_1) {
                val currentValue = currentState.score1
                val newValue = currentValue + 1
                _state.value = currentState.copy(score1 = newValue)
                if (newValue >= WINNER_SCORE) {
                    _state.value = TeamScoreState.Winner(
                        winnerTeam = Team.TEAM_1,
                        score1 = newValue,
                        score2 = currentState.score2
                    )
                }
            } else {
                val currentValue = currentState.score2
                val newValue = currentValue + 1
                _state.value = currentState.copy(score2 = newValue)
                if (newValue >= WINNER_SCORE) {
                    _state.value = TeamScoreState.Winner(
                        winnerTeam = Team.TEAM_2,
                        score1 = currentState.score1,
                        score2 = newValue
                    )
                }
            }
        }
    }

    companion object {
        private const val WINNER_SCORE = 7
    }
}