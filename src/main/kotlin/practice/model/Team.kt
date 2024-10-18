package practice.model

data class Team(
    var id: Long,
    var place: Int,
    var players: List<Player>
) {
    fun getPlayerSize(): Int {
        return this.players.count()
    }
}