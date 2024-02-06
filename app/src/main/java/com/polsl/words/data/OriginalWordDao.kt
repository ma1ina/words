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

    @Query("SELECT * from originalWords WHERE categoryId=:categoryId")
    fun getOriginalWordWithCategory(categoryId: Int): List<OriginalWord>

    @Query("SELECT COUNT(*) AS elementCount FROM originalWords WHERE categoryId = :categoryId")
    fun countOriginalWordsWithCategory(categoryId: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(originalWord: OriginalWord)

    @Update
    suspend fun update(originalWord: OriginalWord)

    @Delete
    suspend fun delete(originalWord: OriginalWord)
}