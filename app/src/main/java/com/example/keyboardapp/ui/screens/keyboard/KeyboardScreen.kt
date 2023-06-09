package com.example.keyboardapp.ui.screens.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardScreen(
    modifier: Modifier = Modifier
) {
    val keysArray = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Shift", "Z", "X", "C", "V", "B", "N", "M", "Delete"),
        arrayOf("123", "Emoji", ",", "SPACE", ".", "Enter")
    )
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
                            "SPACE" -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(3.54f)
                            )
                            "Enter" -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(2f)
                            )
                            else -> KeyboardKey(
                                keyboardKey = key,
                                modifier = modifier.weight(1f)
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