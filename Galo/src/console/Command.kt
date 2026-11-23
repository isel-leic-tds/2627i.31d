package console

import model.*

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

fun getCommands(gs: GameStorage): Map<String, Command> = mapOf(
    "EXIT" to Command(isTerminate =  true),
    "NEW" to Command { _, g -> g?.new() ?: Game() },
    "PLAY" to Play,
    "SCORE" to Command { _, g -> g?.also{ it.score.show() } },
    "SAVE" to storageCommand { name, g ->
        g.also{ gs.create(name, checkNotNull(g) { "No game" } ) }
    },
    "LOAD" to storageCommand { name, _ ->
        requireNotNull(gs.read(name)) { "No game named $name" }
    }
)

fun storageCommand(oper: (Name, Game?) -> Game?) = Command("<name>") { args, g ->
    val arg = requireNotNull(args.firstOrNull()) { "Missing name" }
    oper(Name(arg),g)
}
