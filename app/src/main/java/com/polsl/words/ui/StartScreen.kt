package com.polsl.words.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.data.Language
import com.polsl.words.ui.tile.WordTile
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
fun StartScreen(
    modifier: Modifier = Modifier,
    onLearnClick: () -> Unit,
    viewModel: StartViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()
    var expandedLanguageDropDown by remember { mutableStateOf(false) }
    val language by viewModel.language.collectAsState(initial = Language.EN)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(modifier = Modifier, onTileClick = onLearnClick, word = "Nauka")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(modifier = Modifier, onTileClick = { print("test") }, word = "Test")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(modifier = Modifier, onTileClick = { print("test") }, word = "Test")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(
                onTileClick = { expandedLanguageDropDown = !expandedLanguageDropDown },
                word = "Wybrany język: ${language?.displayFlag()}"
            ) {
                DropdownMenu(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    expanded = expandedLanguageDropDown,
                    onDismissRequest = { expandedLanguageDropDown = false }
                ) {
                    Language.values().forEach { language ->
                        WordTile(onTileClick = {
                            coroutineScope.launch {
                                viewModel.settingsManager.saveSelectedLanguage(language)
                            }
                            expandedLanguageDropDown = false
                        }, word = "${language.displayName()} ${language.displayFlag()}")
                    }
                }
            }
        }

    }
}