package com.polsl.words.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polsl.words.WordsApplication
import com.polsl.words.ui.dictionary.DictionaryCategoryViewModel
import com.polsl.words.ui.dictionary.DictionaryViewModel
import com.polsl.words.ui.exam.ExamCategoryViewModel
import com.polsl.words.ui.exam.ExamViewModel
import com.polsl.words.ui.learn.LearnCategoryViewModel
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
            LearnCategoryViewModel(
                wordsApplication().container.categoryDao,
                wordsApplication().container.originalWordDao,
                wordsApplication().container.settingsManager
            )
        }
        initializer {
            StartViewModel(wordsApplication().container.settingsManager)
        }
        initializer {
            DictionaryCategoryViewModel(
                wordsApplication().container.categoryDao,
                wordsApplication().container.originalWordDao
            )
        }
        initializer {
            DictionaryViewModel(
                wordsApplication().container.originalWordDao,
                wordsApplication().container.translatedWordDao,
                wordsApplication().container.settingsManager
            )
        }
        initializer {
            ExamCategoryViewModel(
                wordsApplication().container.categoryDao,
                wordsApplication().container.originalWordDao,
                wordsApplication().container.settingsManager
            )
        }
        initializer {
            ExamViewModel(
                wordsApplication().container.translatedWordDao,
                wordsApplication().container.originalWordDao,
                wordsApplication().container.settingsManager
            )
        }

    }
}

fun CreationExtras.wordsApplication(): WordsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WordsApplication)

