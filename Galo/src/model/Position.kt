package model

class Position private constructor(val index: Int) {
    val row: Int get() = index / BOARD_SIZE
    val col: Int get() = index % BOARD_SIZE
    val backSlash: Boolean get() = row == col
    val slash: Boolean get() = row + col == BOARD_SIZE - 1
    companion object {
        val all = List(BOARD_DIM) { Position(it) }
        operator fun invoke(i: Int): Position {
            require(i.isValidPosition()) { "Position out of bounds $i" }
            return all[i]
        }
    }
    override fun toString() = "($index)"
}

fun Int.isValidPosition(): Boolean = this in 0..<BOARD_DIM

fun String.toPositionOrNull(): Position? =
    toIntOrNull()?.takeIf { it.isValidPosition() }?.let{ Position.all[it] }