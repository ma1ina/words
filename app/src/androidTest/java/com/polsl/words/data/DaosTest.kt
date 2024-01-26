package com.polsl.words.data

import android.util.Log
import com.polsl.words.TestBase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DaosTest:TestBase() {
    @Test
    fun getWordsTest(){
        val originalWord:OriginalWord
        runBlocking {
           originalWord =  originalWordDao.getOriginalWord(1).first()

        }

        Log.d("info",originalWord.toString())


    }
}