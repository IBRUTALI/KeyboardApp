package com.example.keyboardapp.ui.screens.keyboard

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.example.keyboardapp.utils.stringToColor

class ComposeKeyboardView(context: Context): AbstractComposeView(context) {
    /**
     * The Jetpack Compose UI content for this view.
     * Subclasses must implement this method to provide content. Initial composition will
     * occur when the view becomes attached to a window or when [createComposition] is called,
     * whichever comes first.
     */
    @Composable
    override fun Content() {
        val pre = context.getSharedPreferences("keyboard_color", Context.MODE_PRIVATE)
        val background = pre.getString("background", "White")
        val key = pre.getString("key", "White")
        val text = pre.getString("text", "White")
        val backgroundColor = background?.stringToColor()!!
        val keyColor = key?.stringToColor()!!
        val textColor = text?.stringToColor()!!
        KeyboardScreen(
            backgroundColor = backgroundColor,
            keyColor = keyColor,
            textColor = textColor
        )
    }
}