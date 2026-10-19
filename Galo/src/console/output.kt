package console

import model.*

fun Game.show() {
    // Print board
    board.chunked(3).forEachIndexed { idx, row ->
        println( row.joinToString(" | ", prefix = " "))
        if (idx!=2) println("---+---+---")
    }
    // Print status
    val status = when {
        isWinner('X') -> "Winner X"
        isWinner('O') -> "Winner O"
        board.all{ it != EMPTY } -> "Draw"
        else -> "Turn: $turn"
    }
    println(status)
}

