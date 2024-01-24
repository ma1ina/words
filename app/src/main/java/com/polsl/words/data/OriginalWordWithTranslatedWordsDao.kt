package com.polsl.words.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OriginalWordWithTranslatedWordsDao {
    @Query ("SELECT * FROM originalWords")
    fun getAllWordsFromCategoryAndLanguage(): Flow<List<OriginalWordWithTranslatedWords>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(originalWordWithTranslatedWords: OriginalWordWithTranslatedWords)
}