package com.polsl.words

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polsl.words.ui.WordsViewModel
import com.polsl.words.ui.learn.ChooseWordsScreen


enum class WordsScreen(val title: Int) {
    Start(title = R.string.app_name),
    LearnChooseCategory(title = R.string.learn_choose_category),
    Learn(title = R.string.learn),
    LearnFinal(title = R.string.learn_final)
}

@Composable
fun WordsApp(
    viewModel: WordsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = WordsScreen.valueOf(
        backStackEntry?.destination?.route ?: WordsScreen.Start.name
    )
    NavHost(
        navController = navController,
        startDestination = WordsScreen.Start.name
    ) {
        composable(route = WordsScreen.LearnChooseCategory.name) {
            ChooseWordsScreen(modifier = Modifier)

        }
    }
}