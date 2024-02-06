package com.polsl.words.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polsl.words.ui.AppViewModelProvider
import com.polsl.words.ui.learn.Settings
import com.polsl.words.ui.tile.WordTile

@Composable
fun ChooseCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: ChooseCategoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onCategoryClick: () -> Unit,
) {
    val categoryState by viewModel.chooseCategoryUiState.collectAsState()
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(categoryState.categories) { category ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WordTile(modifier = Modifier, onTileClick = {
                    Settings.choosenCategory = category.categoryId
                    onCategoryClick.invoke()
                }, word = category.name)
            }
        }
    }

}
