package com.polsl.words

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polsl.words.data.OriginalWordWithTranslatedWordsDao
import com.polsl.words.data.WordsDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class TestBase {

    private lateinit var wordsDatabase: WordsDatabase
    private lateinit var originalWordWithTranslatedWordsDao: OriginalWordWithTranslatedWordsDao

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        wordsDatabase = Room.inMemoryDatabaseBuilder(context, WordsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        originalWordWithTranslatedWordsDao = wordsDatabase.originalWordWithTranslatedWordsDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        wordsDatabase.close()
    }

}