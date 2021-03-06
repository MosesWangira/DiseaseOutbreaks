package com.example.diseaseoutbreaks.data.Model.diseases

data class DiseaseItem(
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