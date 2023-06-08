package com.example.keyboardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.keyboardapp.ui.screens.TestScreen
import com.example.keyboardapp.ui.theme.KeyboardAppTheme

class TestActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyboardAppTheme {
                TestScreen()
            }
        }
    }

}