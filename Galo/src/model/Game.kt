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

fun Game.isWinner(player: Player): Boolean {
    val places = Position.all.filter{ board[it.index]==player }
    if (places.size < BOARD_SIZE) return false
    val counts =
        List(BOARD_SIZE){ col-> places.count{ it.col == col}} +
        List(BOARD_SIZE){ row -> places.count{ it.row == row}} +
        places.count{ it.slash } + places.count{ it.backSlash }
    return counts.any{ it == BOARD_SIZE }
}

