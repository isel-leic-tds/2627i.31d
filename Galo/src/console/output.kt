package console

import model.*

val separator = "---" + "+---".repeat(BOARD_SIZE-1)

fun Game.show() {
    // Print board
    Position.all.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println( row.joinToString(" | ", prefix = " "){ board[it].toChar().toString() })
        if (idx!=BOARD_SIZE-1) println(separator)
    }
    // Print status
    val status = when(state) {
        is Win -> "Winner ${state.winner.toChar()}"
        is Draw -> "Draw"
        is Run -> "Turn: ${state.turn}"
    }
    println(status)
}

fun Player?.toChar() = when(this) {
    Player.X -> 'X'
    Player.O -> 'O'
    null -> ' '
}

