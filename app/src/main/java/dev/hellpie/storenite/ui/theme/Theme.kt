package dev.hellpie.storenite.ui.theme

import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

val defaultTheme = ThemeState()
val dayTheme = ThemeState(DayNightPreference.DAY)
val nightTheme = ThemeState(DayNightPreference.NIGHT)

@Composable
fun AppTheme(
    theme: ThemeState = defaultTheme,
    changeSystemBars: Boolean = true,
    content: @Composable () -> Unit
) {
    val isNightTheme = when (theme.dayNightMode) {
        DayNightPreference.DAY -> false
        DayNightPreference.NIGHT -> true
        DayNightPreference.AUTO -> isSystemInDarkTheme()
    }
}
