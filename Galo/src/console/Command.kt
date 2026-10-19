package console

import model.Game

data class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false
) {
    fun execute(args: List<String>, g: Game?): Game? = TODO()
}

fun getCommands(): Map<String, Command> = TODO()