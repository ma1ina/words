package com.polsl.words.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("settings")

    val selectedLanguageFlow: Flow<Language?>
        get() = context.dataStore.data.map { preferences ->
            val languageString = preferences[KEY_SELECTED_LANGUAGE]
            languageString?.let { Language.valueOf(it) }
        }

    suspend fun saveSelectedLanguage(language: Language) {
        context.dataStore.edit { preferences ->
            preferences[KEY_SELECTED_LANGUAGE] = language.name
        }
    }

    private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")

}