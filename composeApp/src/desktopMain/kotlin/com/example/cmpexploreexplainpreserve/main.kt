package com.example.cmpexploreexplainpreserve

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cmp_exploreExplainPreserve",
    ) {
        App()
    }
}