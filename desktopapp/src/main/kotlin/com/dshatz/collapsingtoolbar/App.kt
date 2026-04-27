package com.dshatz.collapsingtoolbar

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root

fun main() {

    application {
        Window(onCloseRequest = ::exitApplication) {
            Root()
        }
    }
}