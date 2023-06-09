package com.example.keyboardapp.ui.screens.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import com.example.keyboardapp.R
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keyboardapp.service.KeyboardService

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    val ctx = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Card(
            modifier = modifier,
            elevation = 5.dp
        ) {
            when (keyboardKey) {
                "Emoji" -> {
                    Icon(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .padding(
                                top = 14.dp,
                                bottom = 14.dp
                            )
                            .clickable(interactionSource = interactionSource, indication = null){},
                        painter = painterResource(id = R.drawable.outline_emoji_emotions_24),
                        contentDescription = "Emoji icon"
                    )
                }
                "Shift" -> {
                    Icon(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .padding(
                                top = 14.dp,
                                bottom = 14.dp
                            )
                            .clickable(interactionSource = interactionSource, indication = null){},
                        painter = painterResource(id = R.drawable.baseline_shift_caps),
                        contentDescription = "Shift icon"
                    )
                }
                "Delete" -> {
                    Icon(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .padding(
                                top = 14.dp,
                                bottom = 14.dp
                            )
                            .clickable(interactionSource = interactionSource, indication = null){},
                        painter = painterResource(id = R.drawable.baseline_backspace_24),
                        contentDescription = "Backspace icon"
                    )
                }
                "Enter" -> {
                    Icon(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .padding(
                                top = 14.dp,
                                bottom = 14.dp
                            )
                            .clickable(interactionSource = interactionSource, indication = null){},
                        painter = painterResource(id = R.drawable.baseline_send),
                        contentDescription = "Send icon"
                    )
                }
                else -> {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .clickable(interactionSource = interactionSource, indication = null) {
                    (ctx as KeyboardService).currentInputConnection.commitText(
                        keyboardKey,
                        keyboardKey
                            .length
                    )
                            }
                            .background(Color.White)
                            .padding(
                                top = 14.dp,
                                bottom = 14.dp
                            ),
                        text = keyboardKey,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (pressed.value) {
                        Text(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(
                                    top = 16.dp,
                                    bottom = 48.dp
                                ),
                            text = keyboardKey,
                            textAlign = TextAlign.Center,
                            color = Color.Blue,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}