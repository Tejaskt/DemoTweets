package com.tejaskt.demotweets.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NoInternetDialog() {
    AlertDialog(
        onDismissRequest = { /* Handle dismiss if needed */ },
        title = { Text("No Internet Connection") },
        text = { Text("Please turn on your internet connection to continue.") },
        confirmButton = {
            TextButton(onClick = { }) {
                Text("OK")
            }
        },
        icon = {
            Icon(Icons.Default.Warning, contentDescription = "Warning Icon")
        }
    )
}

