package com.polsl.words.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OriginalWordDao {
    @Query("SELECT * FROM originalWords")
    fun getAllOriginalWords(): List<OriginalWord>

    @Query("SELECT * from originalWords WHERE originalWordId = :originalWordId")
    fun getOriginalWord(originalWordId: Int): Flow<OriginalWord>

    @Query(
        "SELECT ow.originalWordId, ow.word, ow.categoryId\n" +
                "FROM originalWords ow\n" +
                "JOIN translatedWords tw ON ow.originalWordId = tw.originalWordId\n" +
                "WHERE ow.categoryId = :categoryId\n" +
                "AND tw.language = :language"
    )
    fun getOriginalWordWithCategory(categoryId: Int, language: Language): List<OriginalWord>

    @Query(
        "SELECT COUNT(*)\n" +
                "FROM originalWords ow\n" +
                "JOIN translatedWords tw ON ow.originalWordId = tw.originalWordId\n" +
                "WHERE ow.categoryId = :categoryId\n" +
                "AND tw.language = :language")
    fun countOriginalWordsWithCategory(categoryId: Int,language: Language): Int

    @Query("SELECT COUNT(*) FROM originalWords")
    fun getOriginalWordsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(originalWord: OriginalWord):Long

    @Update
    suspend fun update(originalWord: OriginalWord)

    @Delete
    suspend fun delete(originalWord: OriginalWord)
}