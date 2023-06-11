package com.example.keyboardapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.keyboardapp.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onTestClicked: () -> Unit,
    onInstallClicked: () -> Unit,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val openDialog = remember { mutableStateOf(false) }

        Button(
            shape = RoundedCornerShape(50),
            onClick = {
                openDialog.value = true
                onButtonClicked.invoke()
            }
        ) {
            Text(text = "Change system keyboard")
        }

        if (openDialog.value) {
            CustomAlertDialog(
                openDialog = openDialog,
                onTestClicked = onTestClicked,
                onInstallClicked = onInstallClicked,
                title = stringResource(R.string.change_system_keyboard),
                text = stringResource(R.string.keyboard_options),
                confirmText = stringResource(R.string.install_keyboard),
                dismissText = stringResource(R.string.test),
            )
        }
    }
}

@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean>,
    onTestClicked: () -> Unit,
    onInstallClicked: () -> Unit,
    title: String? = null,
    text: String? = null,
    confirmText: String,
    dismissText: String
) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = title?.let { { Text(text = title) } },
        text = text?.let { { Text(text = text) } },
        confirmButton = {
            Button(onClick = {
                openDialog.value = false
                onInstallClicked.invoke()
            }) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            Button(onClick = {
                openDialog.value = false
                onTestClicked.invoke()
            }) {
                Text(text = dismissText)
            }
        }
    )
}