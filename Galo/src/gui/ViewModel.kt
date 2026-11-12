package gui

import androidx.compose.runtime.*
import model.*

class ViewModel{
    var game by mutableStateOf(Game())
    private set

    fun play(pos: Position) {
        if (game.state is Run) game = game.play(pos)
    }
    fun newGame() {
        if (!showScore) game = game.new()
    }

    var showScore by mutableStateOf(false)
    private set
    fun hideScore() { showScore = false }
    fun showScore() { showScore = true }
}