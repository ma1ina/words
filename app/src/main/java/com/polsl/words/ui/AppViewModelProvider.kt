package com.polsl.words.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polsl.words.WordsApplication
import com.polsl.words.ui.common.ChooseCategoryViewModel
import com.polsl.words.ui.learn.LearnViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LearnViewModel(
                wordsApplication().container.translatedWordDao,
                wordsApplication().container.originalWordDao,
                wordsApplication().container.settingsManager
            )
        }
        initializer {
            ChooseCategoryViewModel(wordsApplication().container.categoryDao)
        }
        initializer {
            StartViewModel(wordsApplication().container.settingsManager)
        }
    }
}

fun CreationExtras.wordsApplication(): WordsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WordsApplication)

