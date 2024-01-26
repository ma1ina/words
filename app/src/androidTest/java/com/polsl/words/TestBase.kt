package com.polsl.words

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polsl.words.data.Category
import com.polsl.words.data.CategoryDao
import com.polsl.words.data.Language
import com.polsl.words.data.OriginalWord
import com.polsl.words.data.OriginalWordDao
import com.polsl.words.data.TranslatedWord
import com.polsl.words.data.TranslatedWordDao
import com.polsl.words.data.WordsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class TestBase {

    protected lateinit var wordsDatabase: WordsDatabase
    protected lateinit var translatedWordDao: TranslatedWordDao
    protected lateinit var originalWordDao: OriginalWordDao
    protected lateinit var categoryDao: CategoryDao

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        wordsDatabase = Room.inMemoryDatabaseBuilder(context, WordsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        originalWordDao = wordsDatabase.originalWordDao()
        translatedWordDao = wordsDatabase.translatedWordDao()
        categoryDao = wordsDatabase.categoryDao()
    }
    @Before
    fun insertDataToDb(){
        runBlocking {
            categoryDao.insert(Category(1,"Podstawowe słówka"))
            originalWordDao.insert(OriginalWord(1,"Jabłko",1))
            translatedWordDao.insert(TranslatedWord(1,1,"Apple",Language.EN))
        }
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        wordsDatabase.close()
    }

}