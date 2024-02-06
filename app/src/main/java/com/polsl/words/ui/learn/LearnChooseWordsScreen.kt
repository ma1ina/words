package com.polsl.words.ui.learn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.ui.AppViewModelProvider
import com.polsl.words.ui.tile.WordTile

@Composable
fun LearnChooseWordsScreen(
    modifier: Modifier = Modifier,
    viewModel: LearnViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onFinalScreenClicked: () -> Unit

) {
    var uiState = viewModel.chooseWordsUiState
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
            WordTile(
                modifier = Modifier,
                onTileClick = { },
                word = uiState.originalWord.word
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(
                modifier = Modifier.weight(1f),
                onTileClick = {
                    onTranslatedWordClicked(
                        viewModel,
                        uiState.displayedTranslatedWords[0],
                        onFinalScreenClicked
                    )
                },
                word = uiState.displayedTranslatedWords[0].translatedWord.word
            )
            WordTile(
                modifier = Modifier.weight(1f),
                onTileClick = {
                    onTranslatedWordClicked(
                        viewModel,
                        uiState.displayedTranslatedWords[1],
                        onFinalScreenClicked
                    )
                },
                word = uiState.displayedTranslatedWords[1].translatedWord.word
            )
            WordTile(
                modifier = Modifier.weight(1f),
                onTileClick = {
                    onTranslatedWordClicked(
                        viewModel,
                        uiState.displayedTranslatedWords[2],
                        onFinalScreenClicked
                    )
                },
                word = uiState.displayedTranslatedWords[2].translatedWord.word
            )
        }
    }
}

fun onTranslatedWordClicked(
    viewModel: LearnViewModel,
    clickedTranslatedWordAnswer: TranslatedWordAnswer,
    onFinalScreenClicked: () -> Unit
) {
    if (viewModel.assertAnswer(clickedTranslatedWordAnswer) && viewModel.wordsLeft == 1) {
        onFinalScreenClicked.invoke()
        return
    }
    viewModel.getRandomWord()
}
