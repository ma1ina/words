package com.polsl.words.data

import androidx.room.Entity

@Entity(tableName = "categories")
data class Category(
    val id:Int,
    val name: String
)
