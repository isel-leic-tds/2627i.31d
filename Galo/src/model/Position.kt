package model

@JvmInline
value class Position(val index: Int) {
    init {
        require(index.isValidPosition()) {
            "Position index must be in range 0..${BOARD_DIM-1}"
        }
    }
    val row: Int get() = index / BOARD_SIZE
    val col: Int get() = index % BOARD_SIZE
    val backSlash: Boolean get() = row == col
    val slash: Boolean get() = row + col == BOARD_SIZE - 1
}

fun Int.isValidPosition(): Boolean = this in 0..<BOARD_DIM

fun String.toPositionOrNull(): Position? =
    toIntOrNull()?.takeIf { it.isValidPosition() }?.let{ Position (it)}