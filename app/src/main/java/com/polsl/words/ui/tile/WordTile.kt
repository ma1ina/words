package com.polsl.words.ui.tile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordTile(
    onTileClick: () -> Unit,
    modifier: Modifier = Modifier,
    word: String
) {
    Card(
        modifier = modifier
            .padding(8.dp).fillMaxWidth(),
        onClick = onTileClick
    ) {
        Text(
            text = word,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )
    }
}
