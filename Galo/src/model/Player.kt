package model
import model.Player.*

enum class Player { X, O }

val Player.other get() = if(this==X) O else X

fun String.toPlayer() =
    Player.entries.first { it.name==this }