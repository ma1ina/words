package com.polsl.words.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslatedWordDao {
    @Query("SELECT * FROM translatedWords")
    fun getAllTranslatedWords(): Flow<List<TranslatedWord>>

    @Query("SELECT * from translatedWords WHERE translatedWordId = :translatedWordId")
    fun getTranslatedWord(translatedWordId: Int): Flow<TranslatedWord>

    @Query("SELECT * from translatedWords WHERE originalWordId = :originalWordId AND language = :language")
    fun getTranslatedWordWithOriginalWordAndLanguage(originalWordId:Int,language: Language):Flow<List<TranslatedWord>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(translatedWord: TranslatedWord)

    @Update
    suspend fun update(translatedWord: TranslatedWord)

    @Delete
    suspend fun delete(translatedWord: TranslatedWord)
}