package com.polsl.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int,
    val name: String
) {
    constructor(name: String) : this(0, name)
}
