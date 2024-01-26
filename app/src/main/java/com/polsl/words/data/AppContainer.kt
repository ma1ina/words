package com.polsl.words.data

import android.content.Context

interface AppContainer{
    val categoryDao:CategoryDao
    val originalWordDao: OriginalWordDao
    val translatedWordDao: TranslatedWordDao

}
class AppDataContainer(private val context:Context):AppContainer {
    override val categoryDao: CategoryDao by lazy {
        WordsDatabase.getDatabase(context).categoryDao()
    }
    override val originalWordDao: OriginalWordDao by lazy {
        WordsDatabase.getDatabase(context).originalWordDao()
    }
    override val translatedWordDao: TranslatedWordDao by lazy {
        WordsDatabase.getDatabase(context).translatedWordDao()
    }

}