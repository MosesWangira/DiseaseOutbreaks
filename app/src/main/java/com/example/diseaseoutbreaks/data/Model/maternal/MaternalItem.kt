package com.example.diseaseoutbreaks.data.Model.maternal

data class MaternalItem(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)