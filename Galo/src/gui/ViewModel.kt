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

    fun exit() { clash.deleteIfOwner() }

    var editNameFor by mutableStateOf<EditFor?>(null)
    private set
    fun start() { editNameFor = EditFor.START }
    fun join() { editNameFor = EditFor.START }
    fun cancelEdit() { editNameFor = null }
    fun action(name: Name) {
        try {
            clash = when (editNameFor as EditFor) {
                EditFor.START -> clash.start(name)
                EditFor.JOIN -> clash.join(name)
            }
            editNameFor = null
        } catch(e: IllegalStateException) {
            errorMessage = e.message
        }
    }

    var errorMessage by mutableStateOf<String?>(null)
    private set
    fun hideError() { errorMessage = null }

/*
    fun play(pos: Position) {
        if (clash.state is Run) game = game.play(pos)
    }
    fun newBoard() {
        if (!showScore) game = game.new()
    }
*/

    var showScore by mutableStateOf(false)
    private set
    fun hideScore() { showScore = false }
    fun showScore() { showScore = true }
}

const val baseName = "games"

class TextFileViewModel
    : ViewModel(TextFileStorage(baseName, GameSerializer))

class MongoViewModel(driver: MongoDriver)
    :ViewModel(MongoStorage(baseName, driver ,GameSerializer))