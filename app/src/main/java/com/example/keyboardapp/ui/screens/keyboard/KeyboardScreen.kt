package com.example.keyboardapp.ui.screens.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardScreen(
    modifier: Modifier = Modifier
) {
    val isCaps = remember {mutableStateOf(IsCaps.CAPS)}
    val keysArray = if(isCaps.value == IsCaps.CAPS) {
        arrayOf(
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            arrayOf("Shift", "Z", "X", "C", "V", "B", "N", "M", "delete"),
            arrayOf("123", "emoji", ",", " ", ".", "enter")
        )
    } else if(isCaps.value == IsCaps.NOCAPS){
        arrayOf(
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
            arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
            arrayOf("shift", "z", "x", "c", "v", "b", "n", "m", "delete"),
            arrayOf("123", "emoji", ",", " ", ".", "enter")
        )
    } else {
        arrayOf(
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            arrayOf("SHIFT", "Z", "X", "C", "V", "B", "N", "M", "delete"),
            arrayOf("123", "emoji", ",", " ", ".", "enter")
        )
    }
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        keysArray.forEach { row ->
            FixedHeightBox(modifier = modifier.fillMaxWidth(), height = 60.dp) {
                Row(modifier) {
                    row.forEach { key ->

                        when (key) {
                            " " -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(3.54f),
                                isCaps = isCaps
                            )
                            "enter" -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(2f),
                                isCaps = isCaps
                            )
                            else -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(1f),
                                isCaps = isCaps
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun FixedHeightBox(
    modifier: Modifier,
    height: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        val h = height.roundToPx()
        layout(constraints.minWidth, h) {
            placeables.forEach { placeable ->
                placeable.place(
                    x = 0,
                    y = kotlin.math.min(0, h - placeable.height)
                )
            }
        }
    }
}