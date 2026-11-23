package model

import storage.Storage

typealias GameStorage = Storage<Name, Game>

open class Clash(val gs: GameStorage)

class ClashRun(
    gs: GameStorage,
    val name: Name,
    val side: Player,
    val game: Game,
) : Clash( gs )

fun Clash.start(name: Name) =
    ClashRun(gs, name, Player.X, Game()).also {
        gs.create(name, it.game)
    }

fun Clash.join(name: Name) =
    gs.read(name)?.let{ ClashRun(gs, name, Player.O, it) }
        ?: error("game $name not found")

fun ClashRun.refresh() =
    ClashRun(gs, name, side, gs.read(name) ?: error("game $name disappear"))

fun ClashRun.deleteIfOwner() {
    if (side == Player.X) gs.delete(name)
}

fun ClashRun.play(p: Position): ClashRun {
    val g = game.play(p)
    check(side == (game.state as Run).turn){ "Not your turn" }
    return ClashRun(gs, name, side, g)
}

val ClashRun.canNew get() = side == when(game.state) {
    is Run -> game.state.turn
    else -> game.first.other
}

fun ClashRun.newBoard(): ClashRun {
    check(canNew){ "cant new" }
    return ClashRun(gs, name, side, game.new())
}
