package com.example.keyboardapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.keyboardapp.R
import java.util.*
import kotlin.random.Random

@Composable
fun TestScreen(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = stringResource(R.string.enter_somehting)) }
        )
        Button(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                putColor(ctx)
            },
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Random color")
        }
    }
}

fun putColor(
    ctx: Context
) {
    val array = arrayOf(
        "Blue",
        "Red",
        "Gray"
    )
    val random = Random.nextInt(2)
    val pre = ctx.getSharedPreferences("keyboard_color", Context.MODE_PRIVATE)
    val editor = pre.edit()
    editor.putString("background", array[random])
    editor.putString("key", array[random])
    editor.putString("text", "White")
    editor.apply()
}