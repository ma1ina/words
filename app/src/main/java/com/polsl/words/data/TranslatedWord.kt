package com.polsl.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translatedWords")
data class TranslatedWord(
    @PrimaryKey(autoGenerate = true)
    val translatedWordId:Int,
    val originalWordId:Int,
    val word:String,
    val language: Language
)

enum class Language{
    EN,ES,DE,FR
}