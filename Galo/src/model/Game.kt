package model

const val EMPTY = ' '

data class Game(
    val first: Char = 'X',
    val turn: Char = first,
    val board: List<Char> = [
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
        EMPTY, EMPTY, EMPTY,
    ]
)

fun Game.new() = Game(first = first.other)

fun Game.canPlay(p: Int) = board[p] == EMPTY

fun Game.play(p: Int) = Game(
    turn = turn.other,
    board = board.mapIndexed { idx, player ->
        if (idx==p) turn else player
    }
)

fun Game.isWinner(player: Char) =
    (0..8 step 4).all{ board[it]==player } ||
    (2..6 step 2).all{ board[it]==player } ||
    (0..2).any{ col -> (0..6 step 3).all{ board[col+it]==player } } ||
    (0..6 step 3).any{ lin -> (0..2).all{ board[lin+it]==player} }


val Char.other get() = if(this=='X') 'O' else 'X'