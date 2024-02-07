package com.polsl.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "originalWords")
data class OriginalWord(
    @PrimaryKey(autoGenerate = true)
    val originalWordId: Int,
    val word: String,
    val categoryId: Int
) {
    constructor(word: String, categoryId: Int) : this(0, word, categoryId)
}
