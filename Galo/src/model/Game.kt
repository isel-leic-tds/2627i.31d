package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE * BOARD_SIZE

typealias Board = Map<Position, Player>

sealed class GameState
data class Run(val turn: Player): GameState()
data class Win(val winner: Player): GameState()
object Draw: GameState()

data class Game(
    val first: Player = X,
    val state: GameState = Run(turn = first),
    val board: Board = emptyMap()
)

fun Game.new() = Game(first = first.other)

fun Game.play(p: Position): Game = when(state) {
  is Run -> {
      check(board[p] == null) { "position $p already taken" }
      val turn = state.turn
      val newBoard = board + (p to turn)
      Game(
          first = first,
          state = when{
              newBoard.isWinnerAt(p) -> Win(turn)
              newBoard.size == BOARD_DIM -> Draw
              else -> Run( turn.other)
          },
          board = newBoard
      )
  }
  is Draw, is Win ->
      error("Game is over")
}

fun Board.isWinnerAt(p: Position): Boolean {
    if (size < BOARD_SIZE*2-1) return false
    val player = get(p)
    val places = filterValues { it == player }.keys
    val counts =
        List(BOARD_SIZE){ col-> places.count{ it.col == col}} +
        List(BOARD_SIZE){ row -> places.count{ it.row == row}} +
        places.count{ it.slash } + places.count{ it.backSlash }
    return counts.any{ it == BOARD_SIZE }
}