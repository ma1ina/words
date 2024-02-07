package com.polsl.words.ui.learn

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LearnViewModel(
    private val translatedWordDao: TranslatedWordDao,
    private val originalWordDao: OriginalWordDao,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private var allCorrectTranslatedWords: List<TranslatedWord> = listOf()
    private var randomTranslatedWords: List<TranslatedWord> = listOf()
    private var allOriginalWords: List<OriginalWord> = listOf()
    private var previousOriginalWords: Set<OriginalWord> = setOf()

    var chooseWordsUiState by mutableStateOf(ChooseWordsUiState())
    var wordsLeft = 0
    var language: Language = Language.EN

    init {
        viewModelScope.launch {
            settingsManager.selectedLanguageFlow.collect { language ->
                language.let {
                    this@LearnViewModel.language = language ?: Language.EN
                }
            }
        }
        viewModelScope.launch {

            val originalWords = withContext(Dispatchers.IO) {
                originalWordDao.getOriginalWordWithCategory(Settings.choosenCategory)
            }
            randomTranslatedWords = withContext(Dispatchers.IO) {
                translatedWordDao.getRandomTranslatedWords(language = language)
            }
            allCorrectTranslatedWords = withContext(Dispatchers.IO) {
                originalWords.map { originalWord ->
                    translatedWordDao.getTranslatedWordWithOriginalWordAndLanguage(
                        originalWord.originalWordId,
                        language
                    )
                }
            }

            allOriginalWords = originalWords
            getRandomWord()
        }
    }

    fun getRandomWord() {
        val availableWords = allOriginalWords.filterNot {
            previousOriginalWords.contains(it)
        }
        wordsLeft = availableWords.size
        if (wordsLeft == 0) {
            throw IllegalStateException("'wordsLeft' should not be 0")
        }
        val randomWord = availableWords.random()
        chooseWordsUiState = chooseWordsUiState.copy(
            originalWord = randomWord,
            displayedTranslatedWords = fillTranslatedWords(randomWord)
        )
        previousOriginalWords += randomWord

    }

    fun assertAnswer(clickedTranslatedWordAnswer: TranslatedWordAnswer): Boolean {
        if (!clickedTranslatedWordAnswer.isCorrect) {
            previousOriginalWords -= chooseWordsUiState.originalWord
            return false
        }
        return true
    }

    private fun fillTranslatedWords(originalWord: OriginalWord): List<TranslatedWordAnswer> {

        val correctTranslatedWord =
            allCorrectTranslatedWords.find { it.originalWordId == originalWord.originalWordId }
                ?: throw IllegalStateException("No correct translated word found for the given original word")
        val otherTranslatedWords = randomTranslatedWords.filterNot { it == correctTranslatedWord }
            .shuffled()
            .take(2)

        return (otherTranslatedWords.map { otherTranslatedWord ->
            TranslatedWordAnswer(
                otherTranslatedWord,
                false
            )
        } + listOf(TranslatedWordAnswer(correctTranslatedWord, true))).shuffled()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ChooseWordsUiState(
    var displayedTranslatedWords: List<TranslatedWordAnswer> = listOf(
        TranslatedWordAnswer(),
        TranslatedWordAnswer(),
        TranslatedWordAnswer()
    ),
    var originalWord: OriginalWord = OriginalWord(0, "Shouldn't show up", 0),
)

data class TranslatedWordAnswer(
    var translatedWord: TranslatedWord = TranslatedWord(0, 0, "Shouldn't show up", Language.EN),
    var isCorrect: Boolean = false
)
