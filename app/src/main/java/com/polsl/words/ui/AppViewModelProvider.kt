package com.polsl.words.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polsl.words.WordsApplication
import com.polsl.words.ui.learn.ChooseCategoryViewModel
import com.polsl.words.ui.learn.LearnViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LearnViewModel(
                wordsApplication().container.translatedWordDao,
                wordsApplication().container.originalWordDao
            )
        }
        initializer {
            ChooseCategoryViewModel(wordsApplication().container.categoryDao)
        }
    }
}

fun CreationExtras.wordsApplication(): WordsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WordsApplication)

