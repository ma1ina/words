package com.polsl.words.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * from categories WHERE categoryId = :id")
    fun getCategory(id: Int): Flow<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Query("SELECT COUNT(*) FROM categories")
    fun getCategoriesCount(): Int
    
    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

}