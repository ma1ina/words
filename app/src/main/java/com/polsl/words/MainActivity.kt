package com.polsl.words

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.polsl.words.data.AppContainer
import com.polsl.words.data.AppDataContainer
import com.polsl.words.ui.theme.WordsTheme

class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        container = AppDataContainer(this)
        super.onCreate(savedInstanceState)
        setContent {
            WordsTheme {
                WordsApp()
            }
        }
    }
}