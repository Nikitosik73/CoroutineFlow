package ru.paramonov.coroutineflow.lesson12.team_app

sealed class TeamScoreState {

    data class Game(
        val score1: Int,
        val score2: Int
    ): TeamScoreState()

    data class Winner(
        val winnerTeam: Team,
        val score1: Int,
        val score2: Int
    ): TeamScoreState()
}
