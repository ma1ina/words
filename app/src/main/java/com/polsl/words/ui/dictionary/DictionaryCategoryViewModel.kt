package com.polsl.words.ui.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polsl.words.data.Category
import com.polsl.words.data.CategoryDao
import com.polsl.words.data.Language
import com.polsl.words.data.OriginalWordDao
import com.polsl.words.ui.learn.Settings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DictionaryCategoryViewModel(
    val categoryDao: CategoryDao,
    val originalWordDao: OriginalWordDao
) : ViewModel() {
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

    fun checkIfThereAreWords(language: Language): Boolean {
        return originalWordDao.getOriginalWordWithCategory(
            Settings.choosenCategory,
            language
        ).size < 2
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ChooseCategoryUiState(val categories: List<Category> = listOf())