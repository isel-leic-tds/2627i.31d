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
        deleteIfOwner()
    }

fun Clash.join(name: Name) =
    gs.read(name)?.let{
        deleteIfOwner()
        ClashRun(gs, name, Player.O, it)
    } ?: error("game $name not found")

fun Clash.exec(fx: ClashRun.()-> Game): ClashRun {
    check(this is ClashRun) { "Clash not started" }
    return ClashRun(gs, name, side, fx() )
}

fun Clash.refresh() = exec {
    gs.read(name).also {
        check(it!=game){ "No changes" }
    } ?: error("game $name disappear")

}

fun Clash.deleteIfOwner() {
    if (this is ClashRun && side == Player.X) gs.delete(name)
}

fun Clash.play(p: Position) = exec {
    game.play(p).also {
        check(side == (game.state as Run).turn){ "Not your turn" }
        gs.update(name, it)
    }
}

val Clash.canNew get() = this is ClashRun && side == when(game.state) {
    is Run -> game.state.turn
    else -> game.first.other
}

fun Clash.newBoard() = exec {
    check(canNew){ "cant new" }
    game.new().also { gs.update(name, it) }
}
