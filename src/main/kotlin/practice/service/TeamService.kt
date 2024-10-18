package practice.service

import practice.model.Player
import practice.model.Statistics
import practice.model.Team

class TeamService {

    private lateinit var teams: List<Team>

    init {
        teams = listOf(
            Team(1, 1, listOf(
                Player(1, "Ilya", "Ignatovich", 22, 82.3, Statistics(2, 3), 1),
                Player(2, "Alex", "Ignatovich", 23, 84.3, Statistics(32, 3), 1),
                Player(3, "Jo", "Ignatovich", 24, 85.3, Statistics(15, 3), 1)
            )),
            Team(2, 2, listOf(
                Player(1, "Ilya", "Stremous", 26, 52.3, null, 1),
                Player(2, "Alex", "Stremous", 27, 24.3, Statistics(32, 3), 1),
                Player(3, "Jo", "Stremous", 22, 5.3, Statistics(15, 3), 1)
            )),
            Team(3, 3, listOf(
                Player(1, "Ilya", "Stremous", 26, 52.3, Statistics(2, 3), 1),
                Player(2, "Alex", "Stremous", 27, 24.3, Statistics(32, 3), 1),
                Player(3, "Jo", "Stremous", 22, 5.3, Statistics(15, 3), 1)
            )),
            Team(4, 4, emptyList())
        )
    }

    fun getTeams(): List<Team> {
        return teams;
    }

}