package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import androidx.compose.ui.window.MenuBar
import galo.generated.resources.*
import model.*
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource(Res.drawable.circle),
        state = WindowState(size= DpSize.Unspecified)
    ) {
        App(onExit = ::exitApplication)
    }
}


@Composable
private fun FrameWindowScope.App(onExit: () -> Unit) {
    var game by remember { mutableStateOf(Game()) }
    MenuBar {
        Menu("Game") {
            Item("New") { game = game.new() }
            Item("Exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Board(game.board, onClick = { pos: Position ->
                if (game.state is Run)
                    game = game.play(pos)
            })
            StatusBar(game.state)
        }
    }
}