package com.polsl.words.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.polsl.words.data.SettingsManager

class StartViewModel(val settingsManager: SettingsManager) : ViewModel() {
    val language by mutableStateOf(settingsManager.selectedLanguageFlow)
}