package com.polsl.words.ui.dictionary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polsl.words.data.Language
import com.polsl.words.data.OriginalWord
import com.polsl.words.data.OriginalWordDao
import com.polsl.words.data.SettingsManager
import com.polsl.words.data.TranslatedWord
import com.polsl.words.data.TranslatedWordDao
import com.polsl.words.ui.learn.Settings
import kotlinx.coroutines.launch

class DictionaryViewModel(
    val originalWordDao: OriginalWordDao,
    val translatedWordDao: TranslatedWordDao,
    val settingsManager: SettingsManager
) : ViewModel() {


    var dictionaryUiState by mutableStateOf(DictionaryUiState())
    var language: Language = Language.EN

    init {
        refreshViewModel()
    }

    private fun refreshViewModel() {
        viewModelScope.launch {
            settingsManager.selectedLanguageFlow.collect { language ->
                language.let {
                    this@DictionaryViewModel.language = language ?: Language.EN
                }
            }
        }
        viewModelScope.launch {
            val wordPairs: MutableList<Pair<OriginalWord, TranslatedWord>> = mutableListOf()
            originalWordDao.getOriginalWordWithCategory(Settings.choosenCategory)
                .map { originalWord ->
                    wordPairs += Pair(
                        originalWord,
                        translatedWordDao.getTranslatedWordWithOriginalWordAndLanguage(
                            originalWord.originalWordId,
                            language
                        )
                    )
                }
            dictionaryUiState = dictionaryUiState.copy(wordPairs = wordPairs)
        }
    }

    fun getTranslatedWord(originalWordId: Int): TranslatedWord {
        return translatedWordDao.getTranslatedWordWithOriginalWordAndLanguage(
            originalWordId,
            language
        )
    }

    fun deleteWord(originalWord: OriginalWord, translatedWord: TranslatedWord) {

        viewModelScope.launch {
            translatedWordDao.delete(translatedWord)
            if (translatedWordDao.getTranslatedWordsCountWithOriginalWordId(originalWordId = originalWord.originalWordId) == 0) {
                originalWordDao.delete(originalWord)
            }
            refreshViewModel()
        }

    }

    fun validateField(input: String): Boolean {
        return input.contains(Regex("^[a-zA-Z]{1,20}$"))
    }

    fun addWords(newOriginalWord: String, newTranslatedWord: String) {
        val originalWord = OriginalWord(newOriginalWord, Settings.choosenCategory)
    }
}

data class DictionaryUiState(val wordPairs: List<Pair<OriginalWord, TranslatedWord>> = listOf())