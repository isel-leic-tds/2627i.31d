package gui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Player
import org.jetbrains.compose.resources.painterResource
import galo.generated.resources.*

val PLAYER_SIZE = 250.dp

@Composable
fun PlayerView(
    player: Player?,
    modifier: Modifier = Modifier.size(PLAYER_SIZE).background(Color.White)
) {
    if (player == null)
        Box(modifier = modifier)
    else {
        val resource = when (player) {
            Player.X -> Res.drawable.cross
            Player.O -> Res.drawable.circle
        }
        Image(painterResource(resource), modifier = modifier, contentDescription = null)
    }
}