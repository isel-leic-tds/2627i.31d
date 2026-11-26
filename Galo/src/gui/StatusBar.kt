package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.*

@Composable
fun StatusBar(state: GameState, side: Player) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) {
    val style = MaterialTheme.typography.displayMedium
    Text("You:", style = style)
    Cell(side, modifier = Modifier.size(40.dp))
    Spacer(Modifier.width(40.dp))
    val [text, player] = when(state) {
        is Run -> "Turn:" to state.turn
        is Win -> "Winner:" to state.winner
        is Draw -> "Draw" to null
    }
    Text(text, style = style)
    if (player!=null) Cell(player, modifier = Modifier.size(40.dp))
}