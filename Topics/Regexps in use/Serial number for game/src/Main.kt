fun findSerialNumberForGame(listGames: List<String>) {
    val gameName = readln()
    val regex = Regex("$gameName@.+")
    val game = listGames.filter { regex.matches(it) }
    if (game.isEmpty()) {
        println("This game is not in the list.")
    } else {
        val gameCodes = game[0].split("@")
        println("Code for Xbox - ${gameCodes[1]}, for PC - ${gameCodes[2]}")
    }
}