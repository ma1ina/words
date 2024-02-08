package com.polsl.words.ui.exam


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.ui.AppViewModelProvider
import com.polsl.words.ui.learn.Settings
import com.polsl.words.ui.tile.WordTile

@Composable
fun ExamCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: ExamCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCategoryClick: () -> Unit,
) {
    val categoryState by viewModel.chooseCategoryUiState.collectAsState()
    val showDialog = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categoryState.categories) { category ->
                WordTile(
                    modifier = Modifier.fillMaxWidth(),
                    onTileClick = {
                        if (viewModel.checkIfThereAreWords(category.categoryId)) {
                            Settings.choosenCategory = category.categoryId
                            onCategoryClick.invoke()
                        } else {
                            showDialog.value = true
                        }
                    },
                    word = category.name
                )
            }
        }
    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Brak słów") },
            text = { Text(text = "Nie ma wystarczającej ilości słów w tej kategorii, dodaj je w słowniku") },
            confirmButton = {
            }
        )
    }
}
