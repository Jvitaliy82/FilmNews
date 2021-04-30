package com.exemple.filmnews

data class Movie(
    val title: String,
    val description: String = "",
    val thumbnail: Int,
    val studio: String = "",
    val rating: String = "",
    val streamingLink: String = ""
)
