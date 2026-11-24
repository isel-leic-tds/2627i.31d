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
        onCloseRequest = { },
        icon = painterResource(Res.drawable.circle),
        state = WindowState(size= DpSize(400.dp,485.dp))
    ) {
        App(onExit = ::exitApplication)
    }
}

@Composable
private fun FrameWindowScope.App(onExit: () -> Unit) {
    val vm = remember { TextFileViewModel() }
    MenuBar {
        Menu("Game") {
            Item("Start game", onClick = vm::start)
            Item("Join game", onClick = vm::join)
            //Item("Refresh", onClick = vm::refresh)
            //Item("New board", onClick = vm::newBoard)
            Item("Score", onClick = vm::showScore)
            Item("Exit", onClick = { vm.exit(); onExit() })
        }
    }
    MaterialTheme {
        Column {
            //Board(vm.game.board, onClick = vm::play )
            //StatusBar(vm.game.state)
        }
        //if (vm.showScore) ScoreInfo(vm.game.score, onClose = vm::hideScore)
        vm.editNameFor?.let {
            EditName(it, onCancel = vm::cancelEdit, onAction = vm::action)
        }
        vm.errorMessage?.let { ErrorMessage(it, onOk= vm::hideError) }
    }
}