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

    val isRun get() = clash is ClashRun
    val game get() = (clash as ClashRun).game
    val sidePlayer get() = (clash as ClashRun).side
    val canNew get() = clash is ClashRun && clash.canNew

    private fun exec( fx: Clash.()-> Clash ) {
        try{ clash = clash.fx() }
        catch (e: IllegalStateException) { errorMessage = e.message }
    }

    var editNameFor by mutableStateOf<EditFor?>(null)
    private set
    fun start() { editNameFor = EditFor.START }
    fun join() { editNameFor = EditFor.JOIN }
    fun cancelEdit() { editNameFor = null }
    fun action(name: Name) = exec {
        when (editNameFor as EditFor) {
            EditFor.START -> start(name)
            EditFor.JOIN -> join(name)
        }.also { editNameFor = null }
    }

    var errorMessage by mutableStateOf<String?>(null)
    private set
    fun hideError() { errorMessage = null }

    fun play(pos: Position) = exec { play(pos) }
    fun newBoard() = exec { if (!showScore) newBoard() else clash }
    fun refresh() = exec { refresh() }
    fun exit() = exec { clash.also { deleteIfOwner() } }

    var showScore by mutableStateOf(false)
    private set
    fun hideScore() { showScore = false }
    fun showScore() { showScore = true }
}

const val baseName = "games"

class TextFileViewModel
    : ViewModel(TextFileStorage(baseName, GameSerializer))

@SuppressWarnings("unused")
class MongoViewModel(driver: MongoDriver)
    :ViewModel(MongoStorage(baseName, driver ,GameSerializer))
// TODO exit->close; default driver