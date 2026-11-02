package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import galo.generated.resources.*
import model.*
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource(Res.drawable.circle),
        state = WindowState(size= DpSize.Unspecified)
    ) {
        App()
    }
}

@Composable
private fun App() {
    var player by remember { mutableStateOf(Player.O) }
    MaterialTheme {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            PlayerView(player, modifier = Modifier.size(200.dp))
            Button(onClick = {
                player = player.other
            }) { Text("Other") }
        }
    }
}