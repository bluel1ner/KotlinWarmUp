package practice

import practice.service.TeamService

fun main(args: Array<String>) {
    var teamService = TeamService()
    var teamToAverageAgeDtoList = teamService.getTeams()
        .filter { it.players.isNotEmpty() }
        .sortedByDescending {
            it.players
                .sumOf { it.age }
                .div(it.players.count())
        }
        .map {
            TeamToAverageAgeDto(it.id, it.players
                .sumOf { it.age }.toDouble()
                .div(it.players.count()))
        }

    println(teamToAverageAgeDtoList)
}


data class TeamToAverageAgeDto(
    var teamId: Long,
    var averageAge: Double
)