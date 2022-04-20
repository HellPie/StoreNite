package dev.hellpie.storenite.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.util.*
import javax.inject.Inject

val Context.preferences: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class PreferencesStore @Inject constructor(@ApplicationContext private val context: Context) {
    val store get() = context.preferences

    fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.preferences.data.map { it[key] ?: return@map defaultValue }

    fun <T : Any> get(keyName: String, serializer: KSerializer<T>, defaultValue: T): Flow<T> =
        optional(keyName, serializer).map { if (it.isPresent) it.get() else defaultValue }

    fun <T : Any> optional(key: Preferences.Key<T>): Flow<Optional<T>> =
        context.preferences.data.map { pref -> pref[key]?.let { Optional.of(it) } ?: Optional.empty() }

    fun <T : Any> optional(keyName: String, serializer: KSerializer<T>): Flow<Optional<T>> {
        return optional(stringPreferencesKey(keyName)).map {
            if (it.isPresent) {
                try {
                    Optional.of(Json.decodeFromString(serializer, it.get()))
                } catch (exception: SerializationException) {
                    Timber.e(exception)
                    Optional.empty()
                }
            } else {
                Optional.empty()
            }
        }
    }

    suspend fun <T> write(key: Preferences.Key<T>, value: T) =
        context.preferences.edit { it[key] = value }

    suspend fun <T> write(keyName: String, value: T, serializer: KSerializer<T>) =
        write(stringPreferencesKey(keyName), Json.encodeToString(serializer, value))

    suspend fun <T> remove(key: Preferences.Key<T>) =
        context.preferences.edit { it.remove(key) }
}
