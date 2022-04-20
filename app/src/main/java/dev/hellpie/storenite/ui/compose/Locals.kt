package dev.hellpie.storenite.ui.compose

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.staticCompositionLocalOf

object Locals {
    val localScaffoldState = staticCompositionLocalOf<ScaffoldState> { error("No localScaffoldState provided") }
    val localVersionState = staticCompositionLocalOf { "Unknown" }
}
