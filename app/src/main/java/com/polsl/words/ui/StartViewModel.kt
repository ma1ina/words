package com.polsl.words.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.polsl.words.data.SettingsManager
import com.polsl.words.ui.learn.Settings

class StartViewModel(val settingsManager: SettingsManager) : ViewModel() {
    val language by mutableStateOf(settingsManager.selectedLanguageFlow)
    init {
        Settings.correctAnswers=0
        Settings.totalAnswers=0
    }
}