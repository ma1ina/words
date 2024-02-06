package com.polsl.words.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polsl.words.data.Category
import com.polsl.words.data.CategoryDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChooseCategoryViewModel(
    categoryDao: CategoryDao,
) : ViewModel() {
    val chooseCategoryUiState: StateFlow<ChooseCategoryUiState> =
        categoryDao.getAllCategories().map { ChooseCategoryUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ChooseCategoryUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ChooseCategoryUiState(val categories: List<Category> = listOf())
