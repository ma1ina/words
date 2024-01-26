package com.polsl.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "originalWords")
data class OriginalWord(
    @PrimaryKey
    val originalWordId:Int,
    val word:String,
    val categoryId: Int
)
