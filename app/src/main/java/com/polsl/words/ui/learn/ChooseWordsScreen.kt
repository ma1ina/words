package com.polsl.words.ui.learn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.polsl.words.ui.tile.WordTile

@Composable
fun ChooseWordsScreen(modifier: Modifier = Modifier) {
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
            WordTile(modifier = Modifier, onTileClick = { print("test") }, word = "Test")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WordTile(modifier = Modifier.weight(1f), onTileClick = { print("test") }, word = "Test")
            WordTile(modifier = Modifier.weight(1f), onTileClick = { print("test") }, word = "Test")
            WordTile(modifier = Modifier.weight(1f), onTileClick = { print("test") }, word = "Test")
        }
    }
}
