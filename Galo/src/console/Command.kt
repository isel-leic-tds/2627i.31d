package console

import model.*

open class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false
) {
    open fun execute(args: List<String>, g: Game?): Game? = g
}

object Play : Command("<position>") {
    override fun execute(args: List<String>, g: Game?) : Game {
        val pos = requireNotNull(args.firstOrNull()) { "Missing position" }
        val position = requireNotNull(pos.toIntOrNull()) { "Invalid position $pos"}
        checkNotNull(g) { "No game" }
        return g.play(position)
    }
}

fun getCommands(): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate =  true),
    "NEW" to object : Command() {
        override fun execute(args: List<String>, g: Game?) = g?.new() ?: Game()
    },
    "PLAY" to Play
)