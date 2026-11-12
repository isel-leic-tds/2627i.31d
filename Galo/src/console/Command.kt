package console

import model.*
import storage.Storage

class Command(
    val syntax: String = "",
    val isTerminate: Boolean = false,
    val execute: (List<String>, Game?) -> Game? = { _, g -> g }
)

val Play = Command("<position>") { args, g ->
    val pos = requireNotNull(args.firstOrNull()) { "Missing position" }
    val p = requireNotNull(pos.toPositionOrNull()) { "Invalid position $pos"}
    checkNotNull(g) { "No game" }
    g.play(p)
}

fun getCommands(gs: Storage<String,Game>): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate =  true),
    "NEW" to Command { _, g -> g?.new() ?: Game() },
    "PLAY" to Play,
    "SCORE" to Command { _, g -> g?.also{ it.score.show() } },
    "SAVE" to Command("<filename>") { args, g ->
        checkNotNull(g) { "No game" }
        val filename = requireNotNull(args.firstOrNull()) { "Missing filename" }
        gs.create(filename, g)  // Save the game state to storage
        g
    },
)
