package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        state = WindowState(size= DpSize(400.dp,485.dp))
    ) {
        App(onExit = ::exitApplication)
    }
}

@Composable
private fun FrameWindowScope.App(onExit: () -> Unit) {
    val vm = remember { ViewModel() }
    MenuBar {
        Menu("Game") {
            Item("New", onClick = vm::newGame)
            Item("Score", onClick = vm::showScore)
            Item("Exit", onClick = onExit)
        }
    }
    MaterialTheme {
        Column {
            Board(vm.game.board, onClick = vm::play )
            StatusBar(vm.game.state)
        }
        if (vm.showScore) ScoreInfo(vm.game.score, onClose = vm::hideScore)
    }
}