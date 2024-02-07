package com.polsl.words

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polsl.words.ui.StartScreen
import com.polsl.words.ui.dictionary.DictionaryCategoryScreen
import com.polsl.words.ui.dictionary.DictionaryScreen
import com.polsl.words.ui.learn.LearnCategoryScreen
import com.polsl.words.ui.learn.LearnFinalScreen
import com.polsl.words.ui.learn.LearnScreen


enum class WordsScreen() {
    Start,
    LearnCategory,
    Learn,
    LearnFinal,
    DictionaryCategory,
    Dictionary
}

@Composable
@ExperimentalMaterial3Api
fun WordsApp(
    navController: NavHostController = rememberNavController(),

    ) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = WordsScreen.valueOf(
        backStackEntry?.destination?.route ?: WordsScreen.Start.name
    )
    NavHost(
        navController = navController,
        startDestination = WordsScreen.Start.name
    ) {
        composable(route = WordsScreen.Learn.name) {
            LearnScreen(onFinalScreenClicked = { navController.navigate(WordsScreen.LearnFinal.name) })
        }

        composable(route = WordsScreen.LearnCategory.name) {
            LearnCategoryScreen(onCategoryClick = { navController.navigate(WordsScreen.Learn.name) })

        }
        composable(route = WordsScreen.Start.name) {
            StartScreen(
                onDictionaryClick = { navController.navigate(WordsScreen.DictionaryCategory.name) },
                onLearnClick = { navController.navigate(WordsScreen.LearnCategory.name) },
            )
        }

        composable(route = WordsScreen.LearnFinal.name) {
            LearnFinalScreen(onStartScreenClick = { navController.navigate(WordsScreen.Start.name) })
        }

        composable(route = WordsScreen.DictionaryCategory.name) {
            DictionaryCategoryScreen(onCategoryClick = { navController.navigate(WordsScreen.Dictionary.name) })
        }
        composable(route = WordsScreen.Dictionary.name) {
            DictionaryScreen(onWordClick = {})
        }

    }
}