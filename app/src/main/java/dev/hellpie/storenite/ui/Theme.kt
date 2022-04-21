package dev.hellpie.storenite.ui

import android.os.Build
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val isDynamicThemingSupported = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

val darkTheme = darkColorScheme()
val lighTheme = lightColorScheme()

val typography = Typography()

val cornerShapes = Shapes(
    extraLarge = RoundedCornerShape(0),
    large = RoundedCornerShape(0),
    medium = RoundedCornerShape(0),
    small = RoundedCornerShape(0),
    extraSmall = RoundedCornerShape(0),
)
