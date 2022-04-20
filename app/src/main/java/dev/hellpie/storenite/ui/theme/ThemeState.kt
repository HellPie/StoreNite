package dev.hellpie.storenite.ui.theme

import kotlinx.serialization.Serializable

@Serializable
data class ThemeState(
    val dayNightMode: DayNightPreference = DayNightPreference.AUTO
)

enum class DayNightPreference {
    DAY,
    NIGHT,
    AUTO
}
