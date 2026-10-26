package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE * BOARD_SIZE

val EMPTY = null

data class Game(
    val first: Player = X,
    val turn: Player = first,
    val board: List<Player?> = List(BOARD_DIM) { EMPTY }
)

fun Game.new() = Game(first = first.other)

fun Game.canPlay(p: Position) = board[p.index] == EMPTY

fun Game.play(p: Position): Game {
    check(canPlay(p)) { "position $p already taken" }
    return Game(
        turn = turn.other,
        board = board.mapIndexed { idx, player ->
            if (idx == p.index) turn else player
        }
    )
}

fun Game.isWinner(player: Player) =
    (0..8 step 4).all{ board[it]==player } ||
    (2..6 step 2).all{ board[it]==player } ||
    (0..2).any{ col -> (0..6 step 3).all{ board[col+it]==player } } ||
    (0..6 step 3).any{ lin -> (0..2).all{ board[lin+it]==player} }

