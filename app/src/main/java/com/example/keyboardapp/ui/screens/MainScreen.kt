package com.example.keyboardapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onTestClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val openDialog = remember { mutableStateOf(false) }

        Button(
            onClick = { openDialog.value = true }
        ) {
            Text(text = "Change system keyboard")
        }

        if (openDialog.value) {
            CustomAlertDialog(openDialog = openDialog, onTestClicked = onTestClicked)
        }
    }
}

@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    onTestClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(text = "Change system keyboard") },
        text = { Text(text = "Keyboard options") },
        confirmButton = {
            Button(onClick = { openDialog.value = false }) {
                Text(text = "Install keyboard")
            }
        },
        dismissButton = {
            Button(onClick = {
                openDialog.value = false
                onTestClicked.invoke()
            }) {
                Text(text = "Test keyboard")
            }
        }
    )
}