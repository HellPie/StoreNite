package dev.hellpie.storenite.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hellpie.storenite.data.PreferencesStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private object Constants {
    const val PREF_KEY = "theme_state"
}

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val preferences: PreferencesStore
) : ViewModel() {
    val themeState = preferences.get(Constants.PREF_KEY, ThemeState.serializer(), defaultTheme)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), defaultTheme)
}
