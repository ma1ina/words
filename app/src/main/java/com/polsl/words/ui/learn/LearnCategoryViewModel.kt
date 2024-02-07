package com.polsl.words.ui.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polsl.words.data.Category
import com.polsl.words.data.CategoryDao
import com.polsl.words.data.Language
import com.polsl.words.data.OriginalWordDao
import com.polsl.words.data.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LearnCategoryViewModel(
    val categoryDao: CategoryDao,
    val originalWordDao: OriginalWordDao,
    val settingsManager: SettingsManager
) : ViewModel() {

    var language: Language = Language.EN
    val chooseCategoryUiState: StateFlow<ChooseCategoryUiState> =
        categoryDao.getAllCategories().map { ChooseCategoryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ChooseCategoryUiState()
            )

    fun addNewCategory(categoryName: String) {
        viewModelScope.launch {
            categoryDao.insert(Category(name = categoryName))

        }
    }

    init {
        viewModelScope.launch {
            settingsManager.selectedLanguageFlow.collect { language ->
                language.let {
                    this@LearnCategoryViewModel.language = language ?: Language.EN
                }
            }
        }
    }

    fun checkIfThereAreWords(categoryId: Int): Boolean {
        return originalWordDao.countOriginalWordsWithCategory(categoryId, language) > 1
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ChooseCategoryUiState(val categories: List<Category> = listOf())
