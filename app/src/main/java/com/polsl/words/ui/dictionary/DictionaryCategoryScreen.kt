package com.polsl.words.ui.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.ui.AppViewModelProvider
import com.polsl.words.ui.learn.Settings
import com.polsl.words.ui.tile.WordTile

@Composable
fun DictionaryCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: DictionaryCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCategoryClick: () -> Unit,
) {
    val categoryState by viewModel.chooseCategoryUiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var newCategoryName by remember { mutableStateOf("") }

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
                        Settings.choosenCategory = category.categoryId
                        onCategoryClick.invoke()
                    },
                    word = category.name
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { showDialog = true }
        ) {
            Icon(
                Icons.Filled.AddCircle,
                contentDescription = "Dodaj kategorię",
                Modifier.size(64.dp)
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Wprowadź nazwę kategorii") },
                text = {
                    TextField(
                        value = newCategoryName,
                        onValueChange = { newCategoryName = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.addNewCategory(newCategoryName)
                            showDialog = false
                        }
                    ) {
                        Text(text = "Add")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}