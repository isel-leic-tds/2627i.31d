package gui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.*

@Composable
fun InfoDialog(title: String, onOk: () -> Unit, content: @Composable () -> Unit) = AlertDialog(
    onDismissRequest = onOk,
    title = { Text(title, style = MaterialTheme.typography.displaySmall) },
    text = content,
    confirmButton = { TextButton(onClick = onOk) { Text("OK") } }
)

@Composable
fun ScoreInfo(score: Score, onClose: () -> Unit) = InfoDialog("Score", onClose) {
    val style = MaterialTheme.typography.displaySmall
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Player.entries.forEach { player ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Cell(player, modifier = Modifier.size(32.dp))
                    Text(": ${score.getValue(player)}", style = style)
                }
            }
        }
        Text("Draws: ${score.getValue(null)}", style = style)
    }
}

@Composable
fun ErrorMessage(text: String, onOk: () -> Unit) = InfoDialog("Error", onOk) {
    Text(text, style = MaterialTheme.typography.bodyLargeEmphasized )
}
