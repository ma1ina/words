package com.polsl.words.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Database(
    entities = [OriginalWord::class, TranslatedWord::class, Category::class],
    version = 1,
    exportSchema = false
)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun originalWordDao(): OriginalWordDao
    abstract fun categoryDao(): CategoryDao
    abstract fun translatedWordDao(): TranslatedWordDao

    companion object {
        @Volatile
        private var Instance: WordsDatabase? = null

        fun getDatabase(context: Context): WordsDatabase {
            return Instance ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, WordsDatabase::class.java, "item_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                // Check if the database is empty
                if (instance.originalWordDao().getOriginalWordsCount() == 0
                ) {
                    // Database is empty, so create from asset
                    createFromAsset(context, "room_db.db")
                }

                Instance = instance
                instance
            }
        }

        private fun createFromAsset(context: Context, assetFileName: String) {
            context.assets.open(assetFileName).use { inputStream ->
                File(context.getDatabasePath("item_database").path).outputStream()
                    .use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
            }
        }
    }
}