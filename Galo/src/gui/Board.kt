package gui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import galo.generated.resources.Res
import galo.generated.resources.circle
import galo.generated.resources.cross
import model.*
import org.jetbrains.compose.resources.painterResource

val CELL_SIDE = 200.dp
val LINE_THICKNESS = 6.dp
val BOARD_SIDE = CELL_SIDE * BOARD_SIZE + LINE_THICKNESS * (BOARD_SIZE-1)

@Composable
fun Board(board: Board, onClick: (Position) -> Unit) {
    Column (
        modifier = Modifier.size(BOARD_SIDE).background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Position.all.chunked(BOARD_SIZE).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEach { position ->
                    Cell(board[position], onClick = { onClick(position) })
                }
            }
        }
    }
}

@Composable
fun Cell(
    player: Player?,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier.size(CELL_SIDE).background(Color.White)
) {
    if (player == null)
        Box(modifier = modifier.clickable(onClick = onClick))
    else {
        val resource = when (player) {
            Player.X -> Res.drawable.cross
            Player.O -> Res.drawable.circle
        }
        Image(painterResource(resource), modifier = modifier, contentDescription = null)
    }
}