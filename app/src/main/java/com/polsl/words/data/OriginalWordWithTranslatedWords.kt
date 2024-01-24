package com.polsl.words.data

import androidx.room.Embedded
import androidx.room.Relation

data class OriginalWordWithTranslatedWords(
    @Embedded
    val originalWord: OriginalWord,
    @Relation(
        parentColumn = "originalWordId",
        entityColumn = "translatedWordId"
    )
    val translatedWord: List<TranslatedWord>
)
