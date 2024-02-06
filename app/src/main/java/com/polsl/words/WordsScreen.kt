package com.polsl.words

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polsl.words.ui.StartScreen
import com.polsl.words.ui.learn.LearnChooseCategoryScreen
import com.polsl.words.ui.learn.LearnChooseWordsScreen
import com.polsl.words.ui.learn.LearnFinalScreen


enum class WordsScreen(val title: Int) {
    Start(title = R.string.app_name),
    LearnChooseCategory(title = R.string.learn_choose_category),
    Learn(title = R.string.learn),
    LearnFinal(title = R.string.learn_final)
}

@Composable
fun WordsApp(
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
        composable(route = WordsScreen.Learn.name) {
            LearnChooseWordsScreen(onFinalScreenClicked = { navController.navigate(WordsScreen.LearnFinal.name) })
        }

        composable(route = WordsScreen.LearnChooseCategory.name) {
            LearnChooseCategoryScreen(onCategoryClick = { navController.navigate(WordsScreen.Learn.name) })

        }
        composable(route = WordsScreen.Start.name) {
            StartScreen(onLearnClick = { navController.navigate(WordsScreen.LearnChooseCategory.name) })
        }

        composable(route = WordsScreen.LearnFinal.name) {
            LearnFinalScreen(onStartScreenClick = { navController.navigate(WordsScreen.Start.name) })
        }

    }
}