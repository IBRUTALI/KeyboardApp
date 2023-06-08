package com.example.keyboardapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.keyboardapp.ui.screens.MainScreen
import com.example.keyboardapp.ui.theme.KeyboardAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyboardAppTheme {
                MainScreen(
                    onTestClicked = {
                        ContextCompat.startActivity(
                            this,
                            Intent(this, TestActivity::class.java),
                            null
                        )
                    }
                )
            }
        }
    }

}