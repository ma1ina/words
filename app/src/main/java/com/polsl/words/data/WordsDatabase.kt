package com.polsl.words.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WordsDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("room_db.db")
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}