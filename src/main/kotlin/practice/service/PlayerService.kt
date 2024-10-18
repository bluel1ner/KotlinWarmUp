package practice.service

import practice.model.Player
import practice.model.Statistics

class PlayerService {
    private lateinit var players: List<Player>

    init {
        players = listOf(
            Player(1, "Ilya", "Ignatovich", 22, 82.3, Statistics(2, 3), 1),
            Player(2, "Alex", "Ignatovich", 23, 84.3, Statistics(32, 3), 1),
            Player(3, "Jo", "Ignatovich", 24, 85.3, Statistics(15, 3), 1),

            Player(1, "Ilya", "Stremous", 26, 52.3, null, 2),
            Player(2, "Alex", "Stremous", 27, 24.3, Statistics(32, 3), 2),
            Player(3, "Jo", "Stremous", 22, 5.3, Statistics(15, 3), 2),

            Player(1, "Ilya", "Stremous", 26, 52.3, Statistics(2, 3), 3),
            Player(2, "Alex", "Stremous", 29, 24.3, Statistics(32, 3), 3),
            Player(3, "Jo", "Stremous", 22, 5.3, Statistics(15, 3), 3)
        )

    }
}