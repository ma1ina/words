package com.polsl.words

import android.app.Application
import com.polsl.words.data.AppContainer
import com.polsl.words.data.AppDataContainer

class WordsApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
