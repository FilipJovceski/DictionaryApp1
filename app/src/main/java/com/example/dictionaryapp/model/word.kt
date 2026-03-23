package com.example.dictionaryapp.model

data class Word(
    val id: Int,
    val term: String,
    val definition: String,
    val example: String,
    val category: String,
    val isFavorite: Boolean = false
)