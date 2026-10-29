import model.*
import kotlin.test.*

class GameTest {
    @Test fun initialGameState() {
        val game = Game()
        assertEquals(Player.X, game.first)
        assertTrue(game.board.isEmpty())
        assertTrue(game.state is Run)
        assertEquals(Player.X, game.state.turn)
    }
    @Test fun playMoveUpdatesBoardAndTurn() {
        val pos = Position.all.random()
        val game = Game().play(pos)
        assertEquals(Player.X, game.board[pos])
        assertTrue(game.state is Run)
        assertEquals(Player.O, game.state.turn)
    }
    @Test fun playMoveOnUsedPositionThrows() {
        val pos = Position.all.random()
        val game = Game().play(pos)
        assertFailsWith<IllegalStateException> { game.play(pos) }
    }
    // Helper function to play a list of moves
    private fun playMoves(vararg moves:Int): Game =
        moves.fold(Game()) { g, pos -> g.play(Position(pos)) }
    @Test fun winDetection() {
        if (BOARD_SIZE != 3) return
        // O X - | O X - | - X -
        val game = playMoves(1,0,4,3,7)  // X wins
        assertTrue(game.state is Win)
        assertEquals(Player.X, game.state.winner)
    }
    @Test fun drawDetection() {
        if (BOARD_SIZE != 3) return
        // X O X | X O O | O X X
        val game = playMoves(0,1,2,4,3,5,7,6,8)
        assertTrue(game.state is Draw)
    }
    @Test fun newGameSwitchesFirstPlayer() {
        val game = playMoves(0,1,2)
        assertTrue(game.state is Run)
        assertFalse(game.board.isEmpty())
        val newGame = game.new()
        assertEquals(game.first.other, newGame.first)
        assertTrue(newGame.board.isEmpty())
        assertTrue(newGame.state is Run)
    }
    @Test fun tryToPlayAfterWinThrows() {
        if (BOARD_SIZE != 3) return
        val game = playMoves(1,0,4,3,7) // X wins
        assertTrue(game.state is Win)
        assertFailsWith<IllegalStateException> {
            val emptyPos = (Position.all - game.board.keys).random()
            game.play(emptyPos) // Cannot play after win
        }
    }
}
