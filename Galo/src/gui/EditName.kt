package gui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import model.*

@Composable
fun EditName(editFor: EditFor, onCancel: () -> Unit, onAction: (Name) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Name to ${editFor.text}") },
        text = {
            OutlinedTextField(name, onValueChange = { name = it } )
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("cancel") }
        },
        confirmButton = {
            TextButton(
                enabled = name.isValidName(),
                onClick = { onAction(Name(name)) }
            ) { Text(editFor.text) }
        }
    )
}
