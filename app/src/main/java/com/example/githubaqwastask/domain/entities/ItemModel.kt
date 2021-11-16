package com.example.githubaqwastask.domain.entities

data class ItemModel(
    val author: String? = "",
    val avatar: String? = "",
    var description: String? = "",
    val forks: Int? = 0,
    val language: String? = "",
    var name:String? = "",
    var stars:Int? = 0,
    var isCurrentInFocus: Boolean? = false


)