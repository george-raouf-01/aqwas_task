package com.example.githubaqwastask.data.service.response

data class Item(
    val author: String? = "",
    val avatar: String? = "",
    val builtBy: List<BuiltBy>? = listOf(),
    val currentPeriodStars: Int? = 0,
    val description: String? = "",
    val forks: Int? = 0,
    val language: String? = "",
    val languageColor: String? = "",
    val name: String? = "",
    val stars: Int? = 0,
    val url: String? = "",
    var isCurrentInFocus: Boolean? = false
)