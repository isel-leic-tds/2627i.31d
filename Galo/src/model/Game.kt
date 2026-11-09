package model
import model.Player.*

const val BOARD_SIZE = 3
const val BOARD_DIM = BOARD_SIZE * BOARD_SIZE

typealias Board = Map<Position, Player>

typealias Score = Map<Player?, Int>

fun initialScore() = (Player.entries + null).associateWith { 0 }

fun Score.advance(player: Player?): Score =
    plus(player to getValue(player) + 1)

sealed class GameState
data class Run(val turn: Player): GameState()
data class Win(val winner: Player): GameState()
object Draw: GameState()

data class Game(
    val first: Player = X,
    val state: GameState = Run(turn = first),
    val board: Board = emptyMap(),
    val score: Score = initialScore()
)

fun Game.new() = Game(
    first = first.other,
    score = if (state is Run) score.advance(state.turn.other)
            else score
)

fun Game.play(p: Position): Game = when(state) {
  is Run -> {
      check(board[p] == null) { "position $p already taken" }
      val turn = state.turn
      val newBoard = board + (p to turn)
      val newState = when{
          newBoard.isWinnerAt(p) -> Win(turn)
          newBoard.size == BOARD_DIM -> Draw
          else -> Run( turn.other)
      }
      Game(
          first = first,
          state = newState,
          board = newBoard,
          score = when(newState) {
              is Win -> score.advance(newState.winner)
              is Draw -> score.advance(null)
              is Run -> score
          }
      )
  }
  is Draw, is Win ->
      error("Game is over")
}

fun Board.isWinnerAt(p: Position): Boolean {
    if (size < BOARD_SIZE*2-1) return false
    val player = get(p)
    val places = filterValues { it == player }.keys
    val counts = buildList {
        addAll(List(BOARD_SIZE){ col-> places.count{ it.col == col}})
        addAll(List(BOARD_SIZE){ row -> places.count{ it.row == row}})
        add(places.count{ it.slash })
        add(places.count{ it.backSlash })
    }
    return counts.any{ it == BOARD_SIZE }
}