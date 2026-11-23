package gui

import androidx.compose.runtime.*
import model.*
import storage.TextFileStorage
import storage.mongo.MongoDriver
import storage.mongo.MongoStorage

enum class EditFor(val text:String) { START("start"), JOIN("join") }

open class ViewModel(gs: GameStorage){
    var clash by mutableStateOf(Clash(gs))
    private set

    var editNameFor: EditFor? = null
    private set
    fun start() { editNameFor = EditFor.START }
    fun join() { editNameFor = EditFor.START }
    fun cancelEdit() { editNameFor = null }
    fun action(name: Name, to: EditFor) {
        clash = when (to) {
            EditFor.START -> clash.start(name)
            EditFor.JOIN -> clash.join(name)
        }
        editNameFor = null
    }

    fun play(pos: Position) {
        if (clash.state is Run) game = game.play(pos)
    }
    fun newBoard() {
        if (!showScore) game = game.new()
    }

    var showScore by mutableStateOf(false)
    private set
    fun hideScore() { showScore = false }
    fun showScore() { showScore = true }
}

class TextFileViewModel
    : ViewModel(TextFileStorage("game", GameSerializer))

class MongoViewModel(driver: MongoDriver)
    :ViewModel(MongoStorage("games", driver ,GameSerializer))