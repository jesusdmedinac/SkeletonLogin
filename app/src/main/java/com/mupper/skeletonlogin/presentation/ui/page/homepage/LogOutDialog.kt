package com.mupper.skeletonlogin.presentation.ui.page.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LogOutDialog(
    openDialog: Boolean,
    onDismissDialog: () -> Unit,
    logOut: () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            title = {
                Text("¿Ya te vas?")
            },
            text = {
                Text("Tu sesión se cerrará sin problemas.")
            },
            buttons = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = logOut) {
                        Text("CERRAR SESIÓN")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onDismissDialog) {
                        Text("CANCELAR")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            },
        )
    }
}