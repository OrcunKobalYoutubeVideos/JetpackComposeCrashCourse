package com.youtube.crashcourse.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MyDialog(
    title: String="Sil",
    icon: ImageVector = Icons.Filled.Delete,
    message: String,
    onDismissRequest:()->Unit,
    confirmButton:()->Unit,
    dismissButton:()->Unit,
){
    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = {
                confirmButton()
            }) {
                Text(text = "Sil")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                dismissButton()
            }) {
                Text(text = "Vazgeç")
            }
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        icon = {
            Icon(imageVector = icon, contentDescription = null)
        }
    )
}
