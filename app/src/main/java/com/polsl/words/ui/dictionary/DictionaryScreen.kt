package com.polsl.words.ui.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.ui.AppViewModelProvider
import com.polsl.words.ui.tile.WordTile

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier,
    viewModel: DictionaryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onWordClick: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var newOriginalWord by remember { mutableStateOf("") }
    var newTranslatedWord by remember { mutableStateOf("") }
    var newOriginalWordIsValid by remember { mutableStateOf(false) }
    var newTranslatedWordIsValid by remember { mutableStateOf(false) }
    var uiState = viewModel.dictionaryUiState

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.wordPairs) { wordsPair ->

                Row(
                    modifier = modifier.fillMaxWidth(), // Ensure the Row takes the full width available
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        Modifier.weight(0.45f), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        WordTile(
                            onTileClick = { /*TODO*/ },
                            word = wordsPair.first.word
                        )
                    }
                    Column(
                        Modifier.weight(0.45f), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        WordTile(
                            onTileClick = { /*TODO*/ },
                            word = wordsPair.second.word
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(0.1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.deleteWord(
                                    wordsPair.first,
                                    wordsPair.second
                                )
                            }

                        ) {
                            Icon(Icons.Filled.Delete, "Delete")
                        }

                    }


                }
                Divider()


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
                title = { Text(text = "Dodaj słowo i tłumaczenie") },
                text = {
                    Column {
                        TextField(
                            singleLine = true,
                            value = newOriginalWord,
                            onValueChange = {
                                newOriginalWord = it
                                newOriginalWordIsValid = viewModel.validateField(it)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Słówko") },
                            isError = !newOriginalWordIsValid
                        )
                        TextField(
                            singleLine = true,
                            value = newTranslatedWord,
                            onValueChange = {
                                newTranslatedWord = it
                                newTranslatedWordIsValid = viewModel.validateField(it)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Tłumaczenie") },
                            isError = !newTranslatedWordIsValid
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.addWords(newOriginalWord, newTranslatedWord)
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