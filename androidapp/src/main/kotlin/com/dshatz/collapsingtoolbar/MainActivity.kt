package com.dshatz.collapsingtoolbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * Example activity to demonstrate usage.
 * Collapsed height is calculated from minimum height of **only pinned** children, not all children.
 *
 * Additionally, demonstrates status bar inset handling.
 */
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Root()
        }
    }
}

