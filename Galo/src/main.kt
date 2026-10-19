import console.show
import model.*

fun main() {
    var game: Game? = null
    while (true) {
        print("> ")
        val cmd = readln().trim().uppercase().split(' ').filter(String::isNotEmpty)
        when( cmd[0] ) {
            "NEW" -> game = game?.new() ?: Game()
            "EXIT" -> break
            "PLAY" -> if (game!=null) {
                val pos = cmd[1].toInt()
                if (game.canPlay(pos))
                    game = game.play(pos)
            }
        }
        game?.show()
    }
}