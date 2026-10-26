package console

import model.*

val separator = "---" + "+---".repeat(BOARD_SIZE-1)

fun Game.show() {
    // Print board
    board.chunked(BOARD_SIZE).forEachIndexed { idx, row ->
        println( row.joinToString(" | ", prefix = " "){ it.toChar().toString() })
        if (idx!=BOARD_SIZE-1) println(separator)
    }
    // Print status
    val status = when {
        isWinner(Player.X) -> "Winner X"
        isWinner(Player.O) -> "Winner O"
        board.all{ it != EMPTY } -> "Draw"
        else -> "Turn: $turn"
    }
    println(status)
}

fun Player?.toChar() = when(this) {
    Player.X -> 'X'
    Player.O -> 'O'
    null -> ' '
}

