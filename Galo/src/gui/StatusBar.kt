package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import model.*

@Composable
fun StatusBar(state: GameState) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) {
    val [text, player] = when(state) {
        is Run -> "Turn:" to state.turn
        is Win -> "Winner:" to state.winner
        is Draw -> "Draw" to null
    }
    Text(text, style = MaterialTheme.typography.displayMedium)
    if (player!=null) Cell(player, modifier = Modifier.size(CELL_SIDE/4))
}