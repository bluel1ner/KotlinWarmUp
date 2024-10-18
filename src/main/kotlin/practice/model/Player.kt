package practice.model

data class Player(
    var id: Long,
    var name: String,
    var lastName: String,
    var age: Int,
    var weight: Double,
    var statistics: Statistics?,
    var teamId: Long?
) {
}