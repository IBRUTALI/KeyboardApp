package com.example.keyboardapp.ui.screens.keyboard

import android.content.Context
import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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
import com.example.keyboardapp.R
import com.example.keyboardapp.service.KeyboardService

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier,
    isCaps: MutableState<IsCaps>
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    val ctx = LocalContext.current
    val iconMap = hashMapOf(
        "emoji" to R.drawable.outline_emoji_emotions_24,
        "shift" to R.drawable.shift_outlined,
        "Shift" to R.drawable.shift_filled,
        "SHIFT" to R.drawable.shfit_double_caps,
        "delete" to R.drawable.baseline_backspace_24,
        "enter" to R.drawable.baseline_send,
        " " to R.drawable.spacebar_filled
    )
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
            if (iconMap.containsKey(keyboardKey)) {
                iconMap[keyboardKey]?.let {
                    KeyboardIcon(
                        ctx = ctx,
                        modifier = modifier,
                        interactionSource = interactionSource,
                        keyboardKey = keyboardKey,
                        drawable = it,
                        isCaps = isCaps,
                        pressed = pressed
                    )
                }
            } else {
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

@Composable
fun KeyboardIcon(
    ctx: Context,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource,
    keyboardKey: String,
    drawable: Int,
    isCaps: MutableState<IsCaps>,
    pressed: State<Boolean>
) {
    Icon(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource, indication = rememberRipple(
                    bounded = true,
                    radius = 250.dp,
                    color = Color.Green
                ),
            ) {
                when (keyboardKey) {
                    " " -> {
                        (ctx as KeyboardService).currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_SPACE))
                    }
                    "Shift" -> {
                        isCaps.value = IsCaps.DOUBLECAPS
                    }
                    "SHIFT" -> {
                        isCaps.value = IsCaps.NOCAPS
                    }
                    "shift" -> {
                        isCaps.value = IsCaps.CAPS
                    }
                    "delete" -> {
                           (ctx as KeyboardService).currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                    }
                    "enter" -> {
                        (ctx as KeyboardService).currentInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
                    }
                }
            }
            .padding(2.dp)
            .padding(
                top = 14.dp,
                bottom = 14.dp
            ),
        painter = painterResource(id = drawable),
        contentDescription = "Send icon"
    )
}