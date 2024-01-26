package com.polsl.words.ui

import androidx.lifecycle.ViewModel
import com.polsl.words.data.Category
import com.polsl.words.data.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WordsViewModel : ViewModel() {
    private val _uiStateLearn = MutableStateFlow(LearnUiState())
    val uiStateLearn: StateFlow<LearnUiState> = _uiStateLearn.asStateFlow()
}

data class WordsUiState(
    val chosenLanguage: Language = Language.EN,


    )

data class LearnUiState(
    val categories: List<Category> = listOf()
)
