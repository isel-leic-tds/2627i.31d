package gui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        print("\nWindow ")
        App()
    }
}

@Composable
private fun App() {
    print("App ")
    var text by remember { mutableStateOf("Hello, World!") }
    MaterialTheme {
        Button(onClick = {
            print("\nClick ")
            text = "Hello TDS"
        }) {
            print("Text=$text")
            Text(text)
        }
    }
}