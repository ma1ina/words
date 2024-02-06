package com.polsl.words.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translatedWords")
data class TranslatedWord(
    @PrimaryKey(autoGenerate = true)
    val translatedWordId: Int,
    val originalWordId: Int,
    val word: String,
    val language: Language
)

enum class Language {
    EN, ES, DE, FR;

    fun displayName(): String {
        return when (this) {
            EN -> "Angielski"
            ES -> "Hiszpański"
            DE -> "Niemiecki"
            FR -> "Francuski"
        }
    }

    fun displayFlag(): String {
        return when (this) {
            EN -> "\uD83C\uDDEC\uD83C\uDDE7"
            ES -> "\uD83C\uDDEA\uD83C\uDDF8"
            DE -> "\uD83C\uDDEA\uD83C\uDDF8"
            FR -> "\uD83C\uDDEB\uD83C\uDDF7"
        }
    }
}